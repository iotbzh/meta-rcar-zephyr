FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#    file://rcar-rproc.patch
#    file://0001-Add-device-driver-for-rcar-r7-rpmsg.patch
#    file://cr7-rproc.cfg
    

SRC_URI_append = " \
    file://0001-clk-add-clock-for-MFIS-registers.patch \
    file://0002-add-mailbox-driver.patch \
    file://0003-add-rcar_rproc-driver.patch \
    file://0004-ebisu-4d.dts-add-definition-for-Cortex-R7-remoteproc.patch \
    file://0005-h3ulcb.dts-add-definition-for-Cortex-R7-remoteproc.patch \
    file://0006-m3ulcb.dts-add-definition-for-Cortex-R7-remoteproc.patch \
    file://0007-clk-add-clock-for-MFIS-registers.patch \
    file://0008-soc-renesas-rcar-rst-Add-support-for-Cortex-R7.patch \
    file://0009-remoteproc-rcar_rproc-add-support-for-starting-the-r.patch \
    file://0010-remoteproc-rcar-disable-auto_boot.patch \
    file://0011-Add-device-driver-for-rcar-r7-rpmsg.patch \
    "

SRC_URI_append = " \
    file://cr7-rproc.cfg \
    "
