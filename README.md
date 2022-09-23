# meta-rcar-zephyr

OpenEmbedded/Yocto Project layer to demonstrates
Zephyr running on Renesas R-Car gen3 platforms.

This layer allows to run Zephyr on:

- H3ULCB
- M3ULCB
- Ebisu

This layers depends on:

```
URI: https://github.com/renesas-rcar/meta-renesas
layers: meta-rcar-gen3
branch: dunfell-dev
revision: HEAD
```

```
URI: git://git.yoctoproject.org/poky
branch: dunfell
revision: HEAD
```

```
URI: git://github.com/openembedded/meta-openembedded
layers: meta-oe
branch: dunfell
revision: HEAD
```

## local.conf:

To add Zephyr demo to your image please add zephyr package group:

```local.conf
IMAGE_INSTALL:append = " packagegroup-rcar-zephyr"
```

You will need to specify an rsa key to sign the zephyr firmwares:

/!\ Replace by your own secret key

```
REMOTE_PROC_PUB_KEY ?= "${TOPDIR}/../meta-rcar-zephyr/demo-key/not-a-secret-key.pub"
REMOTE_PROC_KEY ?= "${TOPDIR}/../meta-rcar-zephyr/demo-key/not-a-secret-key"
```

The public key is embedded into the OP-Tee code, the private key is used at build time
by the zephyr-demo package to generate signed firmware.

