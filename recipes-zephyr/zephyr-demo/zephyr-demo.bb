DESCRIPTION = "Zephyr demos"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI_ebisu = " \
	file://ebisu/zephyr_button.bin \
	file://ebisu/zephyr_blinky.bin \
	file://ebisu/zephyr_openamp_rsc_table.bin \
"

SRC_URI_ulcb = " \
	file://ulcb/zephyr_button.bin \
	file://ulcb/zephyr_blinky.bin \
	file://ulcb/zephyr_openamp_rsc_table.bin \
"

TARG_ebisu = "ebisu"
TARG_ulcb = "ulcb"

inherit deploy
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PROVIDES = "zephyr-demo"
do_compile[noexec] = "1"

do_install () {
	install -d ${D}/boot
	install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.bin ${D}/boot
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.bin ${DEPLOYDIR}/
}
addtask deploy before do_build after do_compile

FILES_${PN} = "/boot"
