DESCRIPTION = "A kvm image with full cmdline"

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
