PR .= "-vuplus0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://disable-mips.patch"
