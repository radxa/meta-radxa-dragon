# Build Yocto for Radxa VMARC-Q9075 IO board

## Build Instructions

Download Yocto layer meta-qcom.

```
git clone https://github.com/radxa/meta-radxa-dragon.git -b meta-qcom-qli-2.0 meta-qcom
```

Build the Yocto image using kas.

```
kas build meta-qcom/ci/radxa-vmarc-q9075-io.yml:meta-qcom/ci/qcom-distro.yml:meta-qcom/ci/performance.yml
```

## Notes

1. fastrpc test command

```
ln -sf /usr/share/qcom/sa8775p/radxa/vmarc-q9075-io/dsp /usr/lib/dsp
fastrpc_test -a v75
```

## Links

- [Build Qualcomm Linux using Yocto](https://dragonwingdocs.qualcomm.com/Key-Documents/Yocto-Guide/build-qualcomm-linux)

## Maintainer(s)

1. Radxa Dev <dev@radxa.com>
