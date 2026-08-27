# Shared UFS image settings for QCOM boards with 4 KiB logical sectors.

require qcom-efi-sd-image.inc

WKS_FILE = "qcom-efi-ufs-4k.wks.in"
WIC_CREATE_EXTRA_ARGS += " --sector-size 4096"
