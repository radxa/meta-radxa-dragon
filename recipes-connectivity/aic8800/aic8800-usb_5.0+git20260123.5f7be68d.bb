SUMMARY = "AIC8800 USB Wi-Fi and Bluetooth kernel modules"
DESCRIPTION = "AIC8800 USB Wi-Fi/Bluetooth support, built from Radxa's DKMS release package against the target kernel."
HOMEPAGE = "https://github.com/radxa-pkg/aic8800"
LICENSE = "GPL-2.0-only & GPL-3.0-or-later & Apache-2.0"
LIC_FILES_CHKSUM = "file://usr/share/doc/aic8800-usb-dkms/copyright;md5=ccd22839cbff32b2bbe9a6ec05d11739"

inherit module

PR = "r7"

AIC8800_DEB_VERSION = "${PV}-7"
AIC8800_RELEASE_TAG = "${AIC8800_DEB_VERSION}"
AIC8800_RELEASE_TAG_URL = "5.0%2Bgit20260123.5f7be68d-7"

SRC_URI = " \
    https://github.com/radxa-pkg/aic8800/releases/download/${AIC8800_RELEASE_TAG_URL}/aic8800-usb-dkms_${AIC8800_RELEASE_TAG_URL}_all.deb;downloadfilename=aic8800-usb-dkms_${AIC8800_DEB_VERSION}_all.deb;name=usb \
    https://github.com/radxa-pkg/aic8800/releases/download/${AIC8800_RELEASE_TAG_URL}/aic8800-firmware_${AIC8800_RELEASE_TAG_URL}_all.deb;downloadfilename=aic8800-firmware_${AIC8800_DEB_VERSION}_all.deb;name=firmware \
"

SRC_URI[usb.sha256sum] = "7dcdd6c97c7d11fae26cde2d3038a325ebbacfd5e9613d9dc7c2c1edc2836d5f"
SRC_URI[firmware.sha256sum] = "627f4415974081eb15de261c60bd34b24247a147aa92a1b4fd3ea5cc420d8878"

S = "${UNPACKDIR}"
AIC8800_SRC = "${S}/usr/src/aic8800-usb-${AIC8800_DEB_VERSION}"
AIC8800_FIRMWARE_SRC = "${S}/lib/firmware/aic8800_fw/USB"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS

	oe_runmake -C ${STAGING_KERNEL_DIR} \
		O=${STAGING_KERNEL_BUILDDIR} \
		M=${AIC8800_SRC}/USB/driver_fw/drivers/aic8800 \
		ARCH=${ARCH} \
		CROSS_COMPILE=${TARGET_PREFIX} \
		CC="${KERNEL_CC}" \
		LD="${KERNEL_LD}" \
		AR="${KERNEL_AR}" \
		OBJCOPY="${KERNEL_OBJCOPY}" \
		STRIP="${KERNEL_STRIP}" \
		KBUILD_EXTRA_SYMBOLS="${KBUILD_EXTRA_SYMBOLS}" \
		modules

	oe_runmake -C ${STAGING_KERNEL_DIR} \
		O=${STAGING_KERNEL_BUILDDIR} \
		M=${AIC8800_SRC}/USB/driver_fw/drivers/aic_btusb \
		ARCH=${ARCH} \
		CROSS_COMPILE=${TARGET_PREFIX} \
		CC="${KERNEL_CC}" \
		LD="${KERNEL_LD}" \
		AR="${KERNEL_AR}" \
		OBJCOPY="${KERNEL_OBJCOPY}" \
		STRIP="${KERNEL_STRIP}" \
		KBUILD_EXTRA_SYMBOLS="${KBUILD_EXTRA_SYMBOLS}" \
		modules
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/updates/dkms
	install -m 0644 ${AIC8800_SRC}/USB/driver_fw/drivers/aic8800/aic_load_fw/aic_load_fw.ko \
		${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/updates/dkms/aic_load_fw.ko
	install -m 0644 ${AIC8800_SRC}/USB/driver_fw/drivers/aic8800/aic8800_fdrv/aic8800_fdrv.ko \
		${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/updates/dkms/aic8800_fdrv.ko
	install -m 0644 ${AIC8800_SRC}/USB/driver_fw/drivers/aic_btusb/aic_btusb.ko \
		${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/updates/dkms/aic_btusb.ko

	install -d ${D}${nonarch_base_libdir}/firmware/aic8800_fw
	cp -r --no-preserve=ownership ${AIC8800_FIRMWARE_SRC} \
		${D}${nonarch_base_libdir}/firmware/aic8800_fw/

	install -d ${D}${modulesloaddir}
	cat > ${D}${modulesloaddir}/aic8800-usb.conf <<-EOF
	aic_load_fw
	aic8800_fdrv
	aic_btusb
	EOF
}

FILES:${PN} += " \
    ${nonarch_base_libdir}/firmware/aic8800_fw/USB \
    ${modulesloaddir}/aic8800-usb.conf \
"
