#include <dirent.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ioctl.h>
#include <time.h>
#include <fcntl.h>
#include <string.h>
#include <linux/rpmsg.h>
#include <stdlib.h>

struct _payload {
	unsigned long num;
	unsigned long size;
	char data[];
};

#define RPMSG_HEADER_LEN 16
#define MAX_RPMSG_BUFF_SIZE (512 - RPMSG_HEADER_LEN)
#define PAYLOAD_MIN_SIZE	1
#define PAYLOAD_MAX_SIZE	(MAX_RPMSG_BUFF_SIZE - 24)
#define NUM_PAYLOADS		(PAYLOAD_MAX_SIZE/PAYLOAD_MIN_SIZE)

static int fd = -1, err_cnt;

struct _payload *i_payload;
struct _payload *r_payload;


int main(int argc, char *argv[])
{
	int i, j;
	int size, bytes_rcvd, bytes_sent;
	char *ept_dev_path;
	int ntimes = 10;

	ept_dev_path="/dev/rpmsg0";
	
	fd = open(ept_dev_path, O_RDWR | O_NONBLOCK);
	if (fd < 0) {
		perror("Failed to open rpmsg device.");
		return -1;
	}

	i_payload = (struct _payload *)malloc(2 * sizeof(unsigned long) + PAYLOAD_MAX_SIZE);
	r_payload = (struct _payload *)malloc(2 * sizeof(unsigned long) + PAYLOAD_MAX_SIZE);

	if (i_payload == 0 || r_payload == 0) {
		printf("ERROR: Failed to allocate memory for payload.\n");
		return -1;
	}

	for (j=0; j < ntimes; j++){
		printf("\r\n **********************************");
		printf("****\r\n");
		printf("\r\n  Echo Test Round %d \r\n", j);
		printf("\r\n **********************************");
		printf("****\r\n");
		for (i = 0, size = PAYLOAD_MIN_SIZE; i < NUM_PAYLOADS;	i++, size++) {
			int k;

			i_payload->num = i;
			i_payload->size = size;

			/* Mark the data buffer. */
			memset(&(i_payload->data[0]), 0xA5, size);

			printf("\r\n sending payload number");
			printf(" %ld of size %d\r\n", i_payload->num,			(2 * sizeof(unsigned long)) + size);
			bytes_sent = write(fd, i_payload, (2 * sizeof(unsigned long)) + size);
			if (bytes_sent <= 0) {
				printf("\r\n Error sending data");
				printf(" .. \r\n");
				break;
			}
			printf("echo test: sent : %d\n", bytes_sent);
			r_payload->num = 0;
			bytes_rcvd = read(fd, r_payload, (2 * sizeof(unsigned long)) + PAYLOAD_MAX_SIZE);
			while (bytes_rcvd <= 0) {
				usleep(10000);
				bytes_rcvd = read(fd, r_payload,	(2 * sizeof(unsigned long)) + PAYLOAD_MAX_SIZE);
			}
			printf(" received payload number ");
			printf("%ld of size %d\r\n", r_payload->num, bytes_rcvd);

			/* Validate data buffer integrity. */
			
			for (k = 0; k < r_payload->size; k++) {
				if (r_payload->data[k] != 0xA5) {
					printf(" \r\n Data corruption");
					printf(" at index %d \r\n", k);
					err_cnt++;
					break;
				}
			}
			bytes_rcvd = read(fd, r_payload, (2 * sizeof(unsigned long)) + PAYLOAD_MAX_SIZE);
		}
		printf("\r\n **********************************");
		printf("****\r\n");
		printf("\r\n Echo Test Round %d Test Results: Error count = %d\r\n",j, err_cnt);
		printf("\r\n **********************************");
		printf("****\r\n");
	}
	free(i_payload);
	free(r_payload);
	close(fd);
	return 0;
}