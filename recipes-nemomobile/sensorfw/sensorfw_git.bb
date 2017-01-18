SUMMARY = "Nemomobile's sensorfw"
HOMEPAGE = "https://git.merproject.org/mer-core/sensorfw"
LICENSE = "LGPL-2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI = "git://git.merproject.org/mer-core/sensorfw.git;protocol=https"
SRCREV = "6e5acd56944faca5e01161a2e444023f5600c299"
PR = "r1"
PV = "+git${SRCREV}"
S = "${WORKDIR}/git"

inherit qmake5

do_configure_prepend() {
    sed -i '/include( doc\/doc.pri )/d' ../git/sensorfw.pro
    sed -i 's@=/usr/include/android@=${STAGING_DIR_TARGET}/usr/include/android@' ../git/core/hybris.pro ../git/config.tests/hybris/hybris.pro ../git/adaptors/hybrisproximityadaptor/hybrisproximityadaptor.pro ../git/adaptors/hybrisorientationadaptor/hybrisorientationadaptor.pro ../git/adaptors/hybrismagnetometeradaptor/hybrismagnetometeradaptor.pro ../git/adaptors/hybrisgyroscopeadaptor/hybrisgyroscopeadaptor.pro ../git/adaptors/hybrisalsadaptor/hybrisalsadaptor.pro ../git/adaptors/hybrisaccelerometer/hybrisaccelerometer.pro
}

do_install_append() {
    install -d ${D}/etc/sensorfw/
    cp ${S}/config/sensord-hybris.conf ${D}/etc/sensorfw/sensord.conf
}

DEPENDS += "qtbase"

FILES_${PN} += "/usr/lib/sensord-qt5/*.so /usr/lib/sensord-qt5/testing/*.so /lib/systemd/system/sensorfwd.service"
FILES_${PN}-dbg += "/usr/share/sensorfw-tests/ /usr/lib/sensord-qt5/.debug/ /usr/lib/sensord-qt5/testing/.debug/"
FILES_${PN}-dev += "/usr/share/qt5/mkspecs/"
