DESCRIPTION = "Zephyr demos"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit python3native

DEPENDS = "sign-rproc-fw-native python3-pyelftools-native python3-pycryptodomex-native"

# We expect that optee-os recipes install the secret key to the DEPLOY_DIR_IMAGE"
DEPENDS += " optee-os"

SRC_URI_ebisu = " \
	file://ebisu/zephyr_button.bin \
	file://ebisu/zephyr_button.elf \
	file://ebisu/zephyr_blinky.bin \
	file://ebisu/zephyr_blinky.elf \
	file://ebisu/zephyr_openamp_rsc_table.bin \
	file://ebisu/zephyr_openamp_rsc_table.elf \
	file://ebisu/zephyr_openamp_char.bin \
	file://ebisu/zephyr_openamp_char.elf \
"

SRC_URI_ulcb = " \
	file://ulcb/zephyr_button.bin \
	file://ulcb/zephyr_button.elf \
	file://ulcb/zephyr_blinky.bin \
	file://ulcb/zephyr_blinky.elf \
	file://ulcb/zephyr_openamp_rsc_table.bin \
	file://ulcb/zephyr_openamp_rsc_table.elf \
	file://ulcb/zephyr_openamp_char.bin \
	file://ulcb/zephyr_openamp_char.elf \
"

TARG_ebisu = "ebisu"
TARG_ulcb = "ulcb"

inherit deploy
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PROVIDES = "zephyr-demo"

do_compile() {
 for fw in ${WORKDIR}/${TARG}/zephyr_*.elf; do
   bbnote "signing ${fw}"
   sign_rproc_fw.py sign --in ${fw} --out ${B}/$(basename ${fw}).signed --key ${DEPLOY_DIR_IMAGE}/not-a-secret-key
 done
}

do_install () {
	install -d ${D}/boot
	install -d ${D}/lib/firmware
	install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.bin ${D}/boot
	install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.elf ${D}/lib/firmware
	install -m 0644 ${B}/*.signed ${D}/lib/firmware
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.bin ${DEPLOYDIR}/
    install -m 0644 ${WORKDIR}/${TARG}/zephyr_*.elf ${DEPLOYDIR}/
}
addtask deploy before do_build after do_compile

FILES_${PN} = "/boot"

# Firmware files are designed to be run on the Cortex-r7
# not on the AARCH64 processor.
INSANE_SKIP = "arch"
FILES_${PN} += "${nonarch_base_libdir}/firmware/*"
