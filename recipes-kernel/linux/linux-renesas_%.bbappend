FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files_upstream:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files_spider:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files_falcon:"

#From upstream
#Patch: rcar_rst_set_gen4_rproc_boot_addr need to be check
SRC_URI_append = " \
    file://0001-soc-renesas-rcar-rst-Add-support-to-set-rproc-boot-a.patch \
    file://0001-dt-bindings-remoteproc-Add-Renesas-R-Car.patch \
    file://0001-remoteproc-Add-Renesas-rcar-driver.patch \
    file://0001-remoteproc-rcar_rproc-Fix-pm_runtime_get_sync-error-.patch \
    file://0001-remoteproc-rcar_rproc-Remove-trailing-semicolon.patch \
    "

#spider
#This patch need to be check twice!!!
SRC_URI_append = " \
    file://0001-clk-add-clock-for-MFIS-registers-for-RCAR-spider.patch \
"

#falcon
#This patch need to be check twice!!!
#The patch 0001-clk-add-clock-for-MFIS-registers-for-RCAR-falcon.patch was done with randon value
SRC_URI_append = " \
    file://0001-falcon-Add-definition-for-Cortex-R7-remoteproc.patch \
    file://0001-clk-add-clock-for-MFIS-registers-for-RCAR-falcon.patch \
"

#Mailbox
#Check if mailbox is activate
SRC_URI_append = " \
    file://0002-add-mailbox-driver.patch \
    "

#rpmsg
SRC_URI_append = " \
    file://0011-Add-device-driver-for-rcar-r7-rpmsg.patch \
    "

#Fake patch need to be rewrite
SRC_URI_append = " \
    file://0001-Add-FAKE-DEFINE-need-to-be-check.patch \
    "

SRC_URI_append = " \
    file://cr7-rproc.cfg \
    "
