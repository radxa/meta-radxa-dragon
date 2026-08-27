SUMMARY = "Minimal UEFI SD image for QCOM boards"
DESCRIPTION = "GPT image with an EFI System Partition and a minimal Linux root filesystem."

require recipes-products/images/qcom-minimal-image.bb
require qcom-efi-sd-image.inc
