SUMMARY = "Minimal UEFI UFS image for QCOM boards"
DESCRIPTION = "4 KiB-sector GPT image with an EFI System Partition and a minimal Linux root filesystem."

require recipes-products/images/qcom-minimal-image.bb
require qcom-efi-ufs-4k-image.inc
