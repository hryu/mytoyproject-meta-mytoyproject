LINUX_RPI_DEV_BRANCH = "rpi-${LINUX_VERSION}.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/patches:"

SRC_URI := "git://github.com/raspberrypi/linux.git;protocol=git;branch=${LINUX_RPI_DEV_BRANCH}"

python() {
    linux_version = d.getVar("LINUX_VERSION").split('.')
    version = int(linux_version[0])
    if version == 4:
        subversion = int(linux_version[1])
        if subversion > 16:
            d.setVar("LIC_FILES_CHKSUM",
	             "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814")
            d.setVar("SRC_URI_append",
                     " file://0001-kconfig-support-yocto-build-s-menuconfig-command.patch")
        else:
            d.setVar("SRC_URI_append",
                     " file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch")
}
