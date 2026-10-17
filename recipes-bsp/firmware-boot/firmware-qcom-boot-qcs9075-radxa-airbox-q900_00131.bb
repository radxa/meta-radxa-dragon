require firmware-qcom-boot-qcs9100.inc

DESCRIPTION = "QCOM NHLOS Firmware for Radxa AIRbox Q900 machine"
FW_ARTIFACTORY = "dl.radxa.com/users/dev/radxa-dragon/qli-2.1.1/radxa-airbox-q900/firmware-boot"
SRC_URI[bootbinaries.sha256sum] = "b35de9350d4ecf8035be839c7959b7cf2151fe79d087ea6606d87221c44c520a"
SRC_URI[bootbinaries-immutable.sha256sum] = "1aa0b67671ef026a40ca133f32128d07fffd614a47cf51eaa5cd5c7d4941d034"