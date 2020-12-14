FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://rcar-rproc.patch \
    file://cr7-rproc.cfg \
    "
