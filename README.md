## Introduction
OpenEmbedded/Yocto Project layer for Radxa Dragon boards based on Qualcomm platforms.

This layers provides aditional recipes and machine configuration files for Qualcomm platform.

This layer depends on:

| URI    | Branch |
| -------- | ------- |
| https://git.yoctoproject.org/meta-qcom | scarthgap |
| https://github.com/openembedded/meta-openembedded | scarthgap |
| https://git.yoctoproject.org/poky | scarthgap |
| https://git.yoctoproject.org/meta-security | scarthgap |
| https://git.yoctoproject.org/meta-virtualization | scarthgap |
| https://github.com/qualcomm-linux/meta-qcom-distro | scarthgap |
| https://github.com/qualcomm-linux/meta-qcom-hwe | scarthgap |

## List of Radxa Boards supported

### QCS9075

* AIRbox Q900

## Build images for Radxa AIRbox Q900

1. Download Qualcomm Yocto and the supporting layers:

```
mkdir workspace
cd workspace
repo init -u https://github.com/quic-yocto/qcom-manifest -b qcom-linux-scarthgap -m qcom-6.6.116-QLI.1.7-Ver.1.1.xml
repo sync
```

2. Add layer meta-radxa-dragon

```
git clone git@github.com:radxa/meta-radxa-dragon.git layers/meta-radxa-dragon -b scarthgap_qcom-6.6.116-QLI.1.7-Ver.1.1
```

3. Add more packages

Add the following lines to file qcs9075-radxa-airbox-q900.conf

```
IMAGE_INSTALL:append = " \
    alsa-utils \
    evtest \
    e2fsprogs \
    gptfdisk \
    i2c-tools \
    iperf3 \
    libgpiod \
    libgpiod-dev \
    libgpiod-tools \
    memtester \
    parted \
    pciutils \
    picocom \
    ppp \
    stress-ng \
    util-linux \
    usbutils \
    linux-firmware-iwlwifi-7265d \
    linux-firmware-ibt-hw-37-8 \
"
```

4. Build machine qcs9075-radxa-airbox-q900

```
export EXTRALAYERS="meta-radxa-dragon"
export MACHINE="qcs9075-radxa-airbox-q900"
MACHINE=${MACHINE} DISTRO=qcom-wayland QCOM_SELECTED_BSP=custom source setup-environment
bitbake qcom-multimedia-image
```

## Maintainer(s)

1. Radxa Dev <dev@radxa.com>
