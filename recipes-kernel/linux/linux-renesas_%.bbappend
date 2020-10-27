FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://stop_Linux_from_turning_off_the_CR7_clock.patch \
    file://rcar-rproc.patch \
    file://cr7-rproc.cfg \
    "
