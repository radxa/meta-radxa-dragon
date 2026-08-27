SUMMARY = "Proprietary multimedia UEFI UFS image for QCOM boards"
DESCRIPTION = "4 KiB-sector GPT image with an EFI System Partition and proprietary QCOM multimedia components in the root filesystem."

require recipes-products/images/qcom-multimedia-proprietary-image.bb
require qcom-efi-ufs-4k-image.inc
