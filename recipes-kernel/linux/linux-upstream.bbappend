#
# Raspberry Pi 3
#
KERNEL_DEVICETREE_raspberrypi3-64 = "broadcom/bcm2837-rpi-3-b-plus.dtb"

SERIAL_raspberrypi3-64 ?= \
	"${@oe.utils.conditional("ENABLE_UART", "1", "console=serial0,115200", "", d)}"

CMDLINE_raspberrypi3-64 ?= \
	"dwc_otg.lpm_enable=0 ${SERIAL} root=/dev/mmcblk0p2 rootfstype=ext4 rootwait"
CMDLINE_raspberrypi3-64_append = \
       ' ${@oe.utils.conditional("ENABLE_KGDB", "1", "kgdboc=serial0,115200", "", d)}'
CMDLINE_raspberrypi3-64_append = \
       ' ${@oe.utils.conditional("DISABLE_RPI_BOOT_LOGO", "1", "logo.nologo", "", d)}'
CMDLINE_raspberrypi3-64_append = \
       ' ${@oe.utils.conditional("ENABLE_DEBUG", "1", "debug", "", d)}'

KERNEL_EXTRA_ARGS_raspberrypi3-64 += "LOADADDR=0x00008000"

do_compile_append_raspberrypi3-64() {
    cc_extra=$(get_cc_option)
    oe_runmake dtbs CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
}

do_deploy_append_raspberrypi3-64() {
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    echo "${CMDLINE}${PITFT_PARAMS}" > ${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt
}

KERNEL_META_BSP := "${THISDIR}/files/kernel-meta/bsp"

do_copy_kernel_metadata() {
	cp -rf ${KERNEL_META_BSP}/* ${WORKDIR}/kernel-meta/bsp/
}

addtask do_copy_kernel_metadata before do_kernel_metadata after do_unpack
