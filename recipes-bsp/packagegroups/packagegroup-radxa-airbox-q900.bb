SUMMARY = "Packages for the Radxa AIRbox Q900 platform"

inherit packagegroup

PACKAGES = " \
    ${PN}-firmware \
    ${PN}-hexagon-dsp-binaries \
"

RRECOMMENDS:${PN}-firmware = " \
    ${@bb.utils.contains_any('DISTRO_FEATURES', 'opencl opengl vulkan', 'linux-firmware-qcom-adreno-a663 linux-firmware-qcom-adreno-a660 linux-firmware-qcom-sa8775p-adreno', '', d)} \
    linux-firmware-qcom-sa8775p-audio \
    linux-firmware-qcom-sa8775p-compute \
    linux-firmware-qcom-sa8775p-generalpurpose \
    linux-firmware-qcom-sa8775p-qupv3fw \
    linux-firmware-qcom-vpu \
    linux-firmware-rtl8852 \
"

RDEPENDS:${PN}-hexagon-dsp-binaries = " \
    hexagon-dsp-binaries-radxa-airbox-q900-adsp \
    hexagon-dsp-binaries-radxa-airbox-q900-cdsp \
    hexagon-dsp-binaries-radxa-airbox-q900-gdsp \
"
