SUMMARY = "Provides a set of package for testing zephyr on R-Car Gen3"
inherit packagegroup

PR = "r1"

RDEPENDS_${PN} = "\
    kernel-module-rpmsg-client-sample \
    kernel-module-rcar-rproc \
    kernel-module-tee-remoteproc \
    kernel-module-rcar-ipcc \
    zephyr-demo \
    rpmsg-echo-test \
    kernel-devicetree \
    "
