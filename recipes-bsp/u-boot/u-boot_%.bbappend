FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://stop_U-Boot_from_turning_off_modules.diff \
    "

SRC_URI_append = " \
    file://0001-Revert-tftp-prevent-overwriting-reserved-memory.patch \
    file://0001-Revert-fs-prevent-overwriting-reserved-memory.patch \
    "
