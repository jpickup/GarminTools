# GarminTools
A suite of tools for creating Garmin FIT files for workouts, schedules and courses

[Workouts and Schedules](./WORKOUTS.md)

[Routes](./ROUTES.md)

## Building
The application is a multi-module Maven project. It uses jlink and jpackage to produce installers for Windows, MacOS and Linux.

The resulting installer files can be found in `app/target/installer` after the maven build has completed on the target platform. 

Alternatively the installers are checked into GitHub here: https://github.com/jpickup/GarminTools/tree/master/installer

The main class is `app/src/main/java/com/johnpickup/app/javafx/AppLauncher.java` if you want to run this from an IDE.

NOTE: 
For Windows the most recent version is available in a ZIP file as I've been unable to get compatible versions of 
the various tools required to build an MSI. Hopefully this will be fixed in the near future.
