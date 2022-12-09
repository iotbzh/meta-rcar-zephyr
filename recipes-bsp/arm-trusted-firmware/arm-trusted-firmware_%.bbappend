FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:ulcb = " \
    file://Secure_accesses_to_write_to_some_registers.diff \
"
