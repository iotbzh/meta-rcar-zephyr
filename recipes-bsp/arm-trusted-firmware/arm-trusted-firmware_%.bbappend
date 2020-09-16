FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://Secure_accesses_to_write_to_some_registers.diff \
"
