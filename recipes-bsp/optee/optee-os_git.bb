DESCRIPTION = "OP-TEE OS"

LICENSE = "BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=c1f21c4f72f372ef38a5a4aee55ec173 \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy python3native

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "3.13.0+iot.bzh+git${SRCPV}"

BRANCH = "iotbzh/rcar-rproc"
SRCREV = "bde99c0089f697cb259ac55eefbeec7a93f5e25d"

SRC_URI = " \
    git://github.com/iotbzh/optee_os;branch=${BRANCH};;protocol=https \
    file://0006-allow-setting-sysroot-for-libgcc-lookup.patch \
"

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"
PLATFORM = "rcar"

DEPENDS = "python3-pycryptodome-native python3-pyelftools-native libgcc"

export CROSS_COMPILE64="${TARGET_PREFIX}"

# Let the Makefile handle setting up the flags as it is a standalone application
LD[unexport] = "1"
LDFLAGS[unexport] = "1"
export CCcore="${CC}"
export LDcore="${LD}"
libdir[unexport] = "1"

S = "${WORKDIR}/git"
EXTRA_OEMAKE = "LIBGCC_LOCATE_CFLAGS=--sysroot=${STAGING_DIR_HOST} -e MAKEFLAGS="

# do_install() nothing
do_install[noexec] = "1"

do_compile() {
     oe_runmake PLATFORM=${PLATFORM} CFG_ARM64_core=y CFG_RPROC_PTA=y CFG_RPROC_SIGN_KEY=${REMOTE_PROC_PUB_KEY} CFG_IN_TREE_EARLY_TAS=remoteproc/80a4c275-0a47-4905-8285-1486a9771a08
     
}

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    # Copy TEE OS to deploy folder
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.elf ${DEPLOYDIR}/tee-${MACHINE}.elf
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee-raw.bin ${DEPLOYDIR}/tee-${MACHINE}.bin
    install -m 0644 ${S}/out/arm-plat-${PLATFORM}/core/tee.srec ${DEPLOYDIR}/tee-${MACHINE}.srec
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/not-a-secret-key ${DEPLOYDIR}/not-a-secret-key
}
addtask deploy before do_build after do_compile
