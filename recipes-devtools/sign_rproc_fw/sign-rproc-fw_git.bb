SUMMARY = "Tools to sign remoteproc firmware"

HOMEPAGE = "https://github.com/STMicroelectronics/optee_os.git"
SECTION = "devel"

LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=c1f21c4f72f372ef38a5a4aee55ec173 \
"
DEPENDS = "python3-pyelftools-native python3-pycryptodomex-native"

BRANCH = "3.12.0-stm32mp"
SRCREV = "32337a5b0fe1df164dbdb0f6347a514358a9be5e"
PV = "3.12.0+git${SRCPV}"

SRC_URI = "git://github.com/STMicroelectronics/optee_os.git;protocol=https;branch=${BRANCH} \
          file://0001-scripts-sign_rproc_fw.py-use-ENUM_P_TYPE_ARM-on-ARM-.patch \
          "

S = "${WORKDIR}/git"

do_compile() {
}

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${S}/scripts/sign_rproc_fw.py ${D}${bindir}/
}

BBCLASSEXTEND = "native"
