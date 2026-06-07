# dtb 修复 Step 2（fix-dtb-via-ostree-devicetree）：
# 当 OSTREE_DEVICETREE="combined-dtb.dtb" 时，本配方 do_install 会执行
#   cp ${DEPLOY_DIR_IMAGE}/combined-dtb.dtb ...
# 该文件由 linux-qcom-mergedtb 的 do_deploy 投放，故须保证其先完成。
do_install[depends] += "linux-qcom-mergedtb:do_deploy"
