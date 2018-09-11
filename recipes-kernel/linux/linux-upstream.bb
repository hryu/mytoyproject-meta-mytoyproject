DESCRIPTION = "Linux Upstream Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/patches:"

LINUX_BRANCH ?= "master"
KMETA = "kernel-meta"

SRC_URI = "git:////home/pokyuser/mytoyproject/linux;protocol=file;branch=${LINUX_BRANCH};name=machine \
	   git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=master;destsuffix=${KMETA} \
           file://0001-kconfig-support-yocto-build-s-menuconfig-command.patch"

SRCREV_machine ?= "${AUTOREV}"
SRCREV_meta ?= "${AUTOREV}"

LINUX_VERSION ?= "4.18-rc+"
PV = "${LINUX_VERSION}+git${SRCPV}"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native ncurses-native"

COMPATIBLE_MACHINE = "(qemuarm64|qemux86-64)"

KBUILD_DEFCONFIG_arm64 = "defconfig"
KBUILD_DEFCONFIG_x86-64 = "x86_64_defconfig"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/taskstats/taskstats.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append_qemuall=" cfg/virtio.scc"
KERNEL_FEATURES_append_qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append_qemux86-64=" cfg/sound.scc"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"

KERNEL_VERSION_SANITY_SKIP = "1"
