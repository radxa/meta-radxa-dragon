# Create an ESP image that has a type #2 EFI UKI and systemd-boot
#
# Copyright (c) 2025 Qualcomm Innovation Center, Inc.
#
# SPDX-License-Identifier: MIT
#

# Optional subfolder, dependant on where the ESP partition gets mounted
# intended to only have a leading slash, no trailing slash e.g. '/EFI', or just empty, ''
ESPFOLDER ?= "/EFI"
UKI_ESP_DEVICETREE ?= ""

do_ukiesp() {
	mkdir -p ${IMAGE_ROOTFS}${ESPFOLDER}/EFI/Linux

	# Copy over files from deploy into the rootfs
	install -m 0755 ${DEPLOY_DIR_IMAGE}/${UKI_FILENAME} ${IMAGE_ROOTFS}${ESPFOLDER}/EFI/Linux

	# Keep a standalone copy of DTBs embedded in the UKI for inspection and
	# recovery. Booting still uses the UKI's embedded .dtb section.
	for dtb in ${UKI_ESP_DEVICETREE}; do
		dtb_name=$(basename ${dtb})
		install -m 0644 ${DEPLOY_DIR_IMAGE}/${dtb_name} ${IMAGE_ROOTFS}${ESPFOLDER}/EFI/Linux/${dtb_name}
	done
}

addtask ukiesp after do_rootfs uki before do_image
