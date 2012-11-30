DESCRIPTION = "Hardware drivers for VuPlus"
SECTION = "base"
LICENSE = "CLOSED"

KV = "${VUPLUS_KERNEL_VERSION}"
PV = "${KV}"
PR = "r19-${SRCDATE}"

PREFERRED_GCC_VERSION = "4.4.3"


DEPENDS = "\
	virtual/kernel \
	module-init-tools \
"

RDEPENDS = "\
	virtual/kernel \
	module-init-tools-depmod \
" 

SRC_URI = "http://archive.vuplus.com/download/drivers/vuplus-dvb-modules-${MACHINE}-${PV}-${PREFERRED_GCC_VERSION}-${SRCDATE}.tar.gz "

S = "${WORKDIR}"

inherit module-base

do_install() {
        install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
        for f in *.ko; do
                install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KERNEL_VERSION}/extra/$f;
        done
	install -d ${D}/etc/modules-load.d
	echo -e "${MODULES_NAME}" > ${D}/etc/modules-load.d/${PN}.conf
	echo -e "${MODULES_NAME}" > ${D}/etc/modules
}

pkg_postinst_${PN} () {
        if [ -d /proc/stb ]; then
                depmod -ae
        fi
        true
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/"
