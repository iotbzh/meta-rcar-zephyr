FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://rcar-rproc.patch \
    file://0001-Add-device-driver-for-rcar-r7-rpmsg.patch \
    file://cr7-rproc.cfg \
    "
