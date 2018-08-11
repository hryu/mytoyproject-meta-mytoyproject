FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0001-events-remove-snd_soc_codec-above-kernel-version-4.1.patch \
	file://0002-event-fix-btrfs__reserve_extent-event-class-for-4.17.patch \
	"

