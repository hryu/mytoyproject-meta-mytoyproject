# mytoyproject-meta-toyproject

# build/boot availability metrics (kernel 4.18+, yocto sumo)

## raspberri3b+ target

- on raspberrypi 3 real target

|                      | raspberrypi3-64 | raspberrypi3-64-yocto |
|----------------------|-----------------|-----------------------|
| linux-upstream       | X (1)           | X (2)                 |
| linux-yocto-dev      | X (1)           | X (2)                 |
| linux-rapberrypi-dev | O (3)(4)        | N/A                   |

(1) : kernel build is good but the final image generation fails.
      KERNEL_DEVICETREE in rapberrypi3-64.conf cannot be overriden in sdcard
      image generation. This is why raspberrypi3-64-yocto machine is needed.
(2) : boot fails (no output and what's going on)
(3) : check menuconfig works
(4) : check lttng-modules compilation

## qemu targets

- for qemux86-64 on qemu/kvm
- for qemuarm64 on qemu

| full-cmdline         | qemuarm64       | qemux86-64            |
|----------------------|-----------------|-----------------------|
| linux-upstream       | O (1)           | O (2)                 |
| linux-yocto-dev      | O (1)           | O (2)                 |

(1) lttng-modules : build fails due to btrs trace event.
(2) lttng-modules : build fails due to libelf-dev. package install doesn't help.
