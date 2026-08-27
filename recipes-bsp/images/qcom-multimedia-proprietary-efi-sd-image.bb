SUMMARY = "Proprietary multimedia UEFI SD image for QCOM boards"
DESCRIPTION = "GPT image with an EFI System Partition and proprietary QCOM multimedia components in the root filesystem."

require recipes-products/images/qcom-multimedia-proprietary-image.bb
require qcom-efi-sd-image.inc
