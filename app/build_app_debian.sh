#!/bin/bash

# ------ ENVIRONMENT --------------------------------------------------------
# The script depends on various environment variables to exist in order to
# run properly. The java version we want to use, the location of the java
# binaries (java home), and the project version as defined inside the pom.xml
# file, e.g. 1.0-SNAPSHOT.
#
# PROJECT_VERSION: version used in pom.xml, e.g. 1.0-SNAPSHOT
# APP_VERSION: the application version, e.g. 1.0.0, shown in "about" dialog

JAVA_VERSION=23
MAIN_JAR="app-$PROJECT_VERSION.jar"

# Set desired installer type: "dmg", "pkg", "deb", "app-image".
INSTALLER_TYPE=app-image

echo "java home: $JAVA_HOME"
echo "project version: $PROJECT_VERSION"
echo "app version: $APP_VERSION"
echo "main JAR file: $MAIN_JAR"
echo "Java home : $JAVA_HOME"

# ------ SETUP DIRECTORIES AND FILES ----------------------------------------
# Remove previously generated java runtime and installers. Copy all required
# jar files into the input/libs folder.
rm -rfd ./target/java-runtime/
rm -rfd target/installer/

mkdir -p target/installer/input/libs/

cp target/libs/* target/installer/input/libs/
cp target/${MAIN_JAR} target/installer/input/libs/

# ------ REQUIRED MODULES ---------------------------------------------------
# Use jlink to detect all modules that are required to run the application.
# Starting point for the jdep analysis is the set of jars being used by the
# application.

echo "detecting required modules"
$JAVA_HOME/bin/jdeps \
  -v \
  --multi-release ${JAVA_VERSION} \
  --ignore-missing-deps \
  --class-path "target/installer/input/libs/*" \
    target/classes/com/johnpickup/app/javafx/MainForm.class
detected_modules=`$JAVA_HOME/bin/jdeps \
  -q \
  --multi-release ${JAVA_VERSION} \
  --ignore-missing-deps \
  --print-module-deps \
  --class-path "target/installer/input/libs/*" \
    target/classes/com/johnpickup/app/javafx/MainForm.class`
echo "detected modules: ${detected_modules}"


# ------ MANUAL MODULES -----------------------------------------------------
# jdk.crypto.ec has to be added manually bound via --bind-services or
# otherwise HTTPS does not work.
#
# See: https://bugs.openjdk.java.net/browse/JDK-8221674
#
# In addition we need jdk.localedata if the application is localized.
# This can be reduced to the actually needed locales via a jlink parameter,
# e.g., --include-locales=en,de.
#
# Don't forget the leading ','!

manual_modules=,jdk.crypto.ec,jdk.localedata
echo "manual modules: ${manual_modules}"

# ------ RUNTIME IMAGE ------------------------------------------------------
# Use the jlink tool to create a runtime image for our application. We are
# doing this in a separate step instead of letting jlink do the work as part
# of the jpackage tool. This approach allows for finer configuration and also
# works with dependencies that are not fully modularized, yet.
echo "creating java runtime image"
$JAVA_HOME/bin/jlink \
  --strip-native-commands \
  --no-header-files \
  --bind-services \
  --no-man-pages  \
  --strip-debug \
  --add-modules "${detected_modules}${manual_modules}" \
  --include-locales=en,de \
  --output target/java-runtime

# ------ PACKAGING ----------------------------------------------------------
# In the end we will find the package inside the target/installer directory.
echo "Creating installer of type $INSTALLER_TYPE"
$JAVA_HOME/bin/jpackage \
--type $INSTALLER_TYPE \
--dest target/installer/ \
--input target/installer/input/libs \
--name garmintools \
--main-class com.johnpickup.app.javafx.AppLauncher \
--main-jar ${MAIN_JAR} \
--java-options -Xmx2048m \
--java-options '--sun-misc-unsafe-memory-access=allow' \
--runtime-image target/java-runtime \
--app-version ${APP_VERSION} \
--vendor "John Pickup" \
--copyright "Copyright © 2025 John Pickup" \

DEB_PKG_ROOT=target/deb/garmintools
rm -rf ${DEB_PKG_ROOT}
mkdir -p ${DEB_PKG_ROOT}/opt
mkdir -p ${DEB_PKG_ROOT}/usr/bin
mkdir -p ${DEB_PKG_ROOT}/DEBIAN
cp src/main/resources/control ${DEB_PKG_ROOT}/DEBIAN/
mv target/installer/garmintools ${DEB_PKG_ROOT}/opt/
cd ${DEB_PKG_ROOT}/usr/bin || exit
ln -s ../../opt/garmintools/bin/garmintools garmintools
cd - || exit
cd target/deb || exit
dpkg-deb -b garmintools
cd - || exit
cp target/deb/garmintools*deb ../installer