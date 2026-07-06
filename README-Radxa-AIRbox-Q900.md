# Build Yocto for Radxa AIRbox Q900

## Build Instructions

Download Yocto layer meta-qcom.

```
git clone https://github.com/radxa/meta-radxa-dragon.git -b meta-qcom-qli-2.0 meta-qcom
```

Build the Yocto image using kas.

```
kas build meta-qcom/ci/radxa-airbox-q900.yml:meta-qcom/ci/qcom-distro.yml:meta-qcom/ci/performance.yml
```

## Links

- [Build Qualcomm Linux using Yocto](https://dragonwingdocs.qualcomm.com/Key-Documents/Yocto-Guide/build-qualcomm-linux)

## Maintainer(s)

1. Radxa Dev <dev@radxa.com>