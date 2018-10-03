DESCRIPTION = "A kvm image with full cmdline"

DISTRO_FEATURES += "${DISTRO_FEATURES_DEFAULT}"
DISTRO_FEATURES += "${POKY_DEFAULT_DISTRO_FEATURES}"
DISTRO_FEATURES += "${DISTRO_FEATURES_LIBC}"
DISTRO_FEATURES += "virtualization"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    qemu \
    libvirt \
    libvirt-libvirtd \
    libvirt-virsh \
    "

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image
