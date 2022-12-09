SUMMARY = "Provides a set of package for testing zephyr on R-Car Gen3"
inherit packagegroup

PR = "r1"


# kernel-module-rcar-rproc
# kernel-module-tee-remoteproc

RDEPENDS:${PN} = "\
    kernel-module-rpmsg-client-sample \
    zephyr-demo \
    rpmsg-echo-test \
    kernel-devicetree \
    "
