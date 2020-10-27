FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://stop_U-Boot_from_turning_off_modules.diff \
    file://0001-hack-do-not-check-for-reserved-memory.patch \
    "
