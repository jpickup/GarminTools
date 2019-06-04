# GarminTools
A suite of tools for creating Garmin FIT files for workouts, schedules and courses

[Workouts and Schedules](./WORKOUTS.md)

## Building
The ant build script is `build.xml`. 

NOTE: The macOS bundle isn't working at present. The fat JAR does though.
```
ant fatjar
java -jar ./jar/GarminToolsFull.jar
```