# 修复：上游 gflags 已将默认分支 master 重命名为 main。
# meta-openembedded(scarthgap) 的 gflags_2.2.2.bb 仍写死 branch=master，
# 全新检出时 git 镜像只有 main、无 master，导致 do_unpack 的分支校验
#   git branch --contains <SRCREV> --list master
# 返空而报 "No up to date source"。
# 锁定的 SRCREV e171aa2d 是 main 的祖先，改指 main 即可（无需改 SRCREV）。
# 上游 meta-oe 修正该 recipe 后可删除本 append。
SRC_URI = "git://github.com/gflags/gflags.git;branch=main;protocol=https"
