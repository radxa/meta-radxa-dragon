SUMMARY = "Install QUP GENI SE firmware (qupv3fw.elf) into the rootfs"
DESCRIPTION = "板级基础能力：当本板某条 i2c/uart/spi GENI SE 未被 bootloader 预配为目标 \
协议时，该 SE 节点需声明 qcom,load-firmware，内核 geni 驱动会从 /lib/firmware/qupv3fw.elf \
加载 SE 固件后总线方能上线。upstream linux-firmware 不含 qcs6490 版 qupv3fw.elf，它只存在于 \
Qualcomm 引导固件包 firmware-qcom-bootbins(QCM6490_bootbinaries) 且仅 deploy 到镜像目录、 \
不进 rootfs。本配方把它装进 rootfs 标准固件路径，作为板级常备能力（与 wifibt-firmware 同性质）。"

# 仅 repackage 一个 Qualcomm 专有固件 blob，用 CLOSED 免去 license 文件铺设。
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "qcs6490-radxa-dragon-q6a"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# qupv3fw.elf 由 firmware-qcom-bootbins 从 QCM6490_bootbinaries 取出并 deploy 到
# DEPLOY_DIR_IMAGE；显式依赖其 do_deploy 保证文件就绪后再安装。
DEPENDS = "firmware-qcom-bootbins"
do_install[depends] += "firmware-qcom-bootbins:do_deploy"

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${DEPLOY_DIR_IMAGE}/qupv3fw.elf \
        ${D}${nonarch_base_libdir}/firmware/qupv3fw.elf
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/qupv3fw.elf"

# qupv3fw.elf 是 GENI SE 的固件 ELF（非主机/目标 CPU arch），跳过 arch QA。
INSANE_SKIP:${PN} = "arch"
