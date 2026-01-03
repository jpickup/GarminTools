# GarminTools
A suite of tools for creating Garmin FIT files for workouts, schedules and courses

[Workouts and Schedules](./WORKOUTS.md)

[Routes](./ROUTES.md)

## Building
The application is a multi-module Maven project. It uses jlink and jpackage to produce installers for Windows, MacOS and Linux.

The resulting installer files can be found in `app/target/installer` after the maven build has completed on the target platform. 

The main class is `app/src/main/java/com/johnpickup/app/javafx/AppLauncher.java` if you want to run this from an IDE.

## Installers
The project now uses GitHub actions to build Windows and Linux installations. These can be found in the project's packages, 
here: https://github.com/jpickup/GarminTools/packages/
Click on `com.johnpickup.garmin.bundle` and search for "windows.zip", "linux.zip" or "macos.zip", download and unpack. 
I need to figure out a better way to link to these.

Previously the installers were checked directly into GitHub but once the size exceeded the GitHub limit this was no longer
suitable.

NOTE: 
For Windows the most recent version is available in a ZIP file as I've been unable to get compatible versions of 
the various tools required to build an MSI. 

## Alternatives
Keeping the various installers up-to-date and making sure they work has been a little more challenging than I anticipated. 
As a result I created a different implementation of the same code packaged as a Java REST server and a Vue.js web UI, 
meaning you no longer need to install anything on your local machine and can instead use a web page 
for the conversion.

The code for all this is under a different GitHub project: https://github.com/jpickup/garmin-tools-server 

The service should be running here: https://garmin-tools.pickup-dev.uk/

It's completely stateless with no persistence or storage. 


