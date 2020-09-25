DESCRIPTION = "Zephyr demos"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI_ebisu = " \
	file://ebisu/button-demo.bin \
	file://ebisu/blinky-demo.bin \
"

SRC_URI_ulcb = " \
	file://ulcb/button-demo.bin \
	file://ulcb/blinky-demo.bin \
"

TARG_ebisu = "ebisu"
TARG_ulcb = "ulcb"

inherit deploy
COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu)"
PROVIDES = "zephyr-demo"
do_compile[noexec] = "1"

do_install () {
	install -d ${D}/boot
	install -m 0644 ${WORKDIR}/${TARG}/*-demo.bin ${D}/boot
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/${TARG}/*-demo.bin ${DEPLOYDIR}/
}
addtask deploy before do_build after do_compile

FILES_${PN} = "/boot"
