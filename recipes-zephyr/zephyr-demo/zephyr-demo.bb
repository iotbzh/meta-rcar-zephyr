DESCRIPTION = "Zephyr demos"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit python3native

DEPENDS = "sign-rproc-fw-native python3-pyelftools-native python3-pycryptodomex-native"

SRC_URI:ebisu = " \
	file://ebisu/zephyr_button.bin \
	file://ebisu/zephyr_button.elf \
	file://ebisu/zephyr_blinky.bin \
	file://ebisu/zephyr_blinky.elf \
	file://ebisu/zephyr_openamp_rsc_table.bin \
	file://ebisu/zephyr_openamp_rsc_table.elf \
	file://ebisu/zephyr_openamp_char.bin \
	file://ebisu/zephyr_openamp_char.elf \
"

SRC_URI:ulcb = " \
	file://ulcb/zephyr_button.bin \
	file://ulcb/zephyr_button.elf \
	file://ulcb/zephyr_blinky.bin \
	file://ulcb/zephyr_blinky.elf \
	file://ulcb/zephyr_openamp_rsc_table.bin \
	file://ulcb/zephyr_openamp_rsc_table.elf \
	file://ulcb/zephyr_openamp_char.bin \
	file://ulcb/zephyr_openamp_char.elf \
"

TARG:ebisu = "ebisu"
TARG:ulcb = "ulcb"
#TARG = "TOTO"


inherit deploy
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PROVIDES = "zephyr-demo"

do_compile() {
 bbnote "Using key: ${REMOTE_PROC_KEY}"
 for fw in ${WORKDIR}/${TARG}/zephyr_*.elf; do
   bbnote "signing ${fw}"
   sign_rproc_fw.py sign --in ${fw} --out ${B}/$(basename ${fw}).signed --key ${REMOTE_PROC_KEY}
 done
}

do_install () {
	install -d ${D}/boot
	install -d ${D}/${nonarch_base_libdir}/firmware
	install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.bin ${D}/boot
	install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.elf ${D}/${nonarch_base_libdir}/firmware
	install -m 0644 ${B}/*.signed ${D}/${nonarch_base_libdir}/firmware
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.bin ${DEPLOYDIR}/
    install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.elf ${DEPLOYDIR}/
}
addtask deploy before do_build after do_compile

FILES:${PN} = "/boot"

# Firmware files are designed to be run on the Cortex-r7
# not on the AARCH64 processor.
INSANE_SKIP = "arch"
FILES:${PN} += "${nonarch_base_libdir}/firmware/*"
