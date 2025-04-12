# GarminTools
A suite of tools for creating Garmin FIT files for workouts, schedules and courses

[Workouts and Schedules](./WORKOUTS.md)

[Routes](./ROUTES.md)

## Building
The application is a multi-module Maven project. It uses jlink and jpackage to produce installers for Windows and MacOS.

The resulting installer files can be found in `app/target/installer` after the maven build has completed on the target platform. 

Alternatively the installers are checked into GitHub here: https://github.com/jpickup/GarminTools/tree/master/installer

NOTE: for Windows the most recent version is available in a ZIP file as I've been unable to get compatible versions of 
the various tools required to build an MSI. Hopefully this will be fixed in the near future.
