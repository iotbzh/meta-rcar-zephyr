FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://stop_Linux_from_turning_off_the_CR7_clock.patch \
    file://rcar-rproc.patch \
    file://cr7-rproc.cfg \
    "



SRC_URI_append = " \
    file://rpmsg_char_driver.cfg \
    file://0001-Add-device-driver-for-rcar-r7-rpmsg.patch \
    "
