# Garmin Workouts
Create workouts and workout schedules for Garmin devices using an Excel spreadsheet to define them. 

What's the benefit? Why use this rather than Garmin Connect?

Garmin Connect allows you to build workouts and schedule them, however it's quite tedious to use it to build a long 
training programme, say the sort you would to train for a marathon. It's also not possible to take a programme that 
you've created and then reuse it to train for the next race.

The idea with this application is to allow the definition of workouts (using a simple text format) and a schedule 
which can then be transferred to a Garmin watch. This definition is created in Microsoft Excel, so the application 
simply translates the spreadsheet into a set of Garmin FIT files that can then be copied over to the watch.

## Workout Language
Workouts are defined as a series of steps, where a step is either a period of time or a distance. 
For example 10 minutes or 400 metres. A step can also have a target such as a pace or a heart rate. 
Steps are written as text, for example:

| Workout | Description |
| ------- | ----------- |
| `1mi` | A step of 1 mile |
| `400m` | A step of 400 metres |
| `100m@4:00-5:00/km` | A step of 100 metres with a target pace between 4 and 5 minutes per kilometre |
| `1mi>6mph` | 1 mile at a pace faster than 6 miles per hour |
| 800m@Z3 | 800 metres in heart rate Zone 3 |
| 400m@160-180bpm | 400 metres wth a heart rate between 160 and 180 beats per minute |

Steps can also be strung together with a `+` character, repeated using `*n` and grouped using brackets, for example:

| Workout | Description |
| ------- | ----------- |
| `1mi + (400m@7:00-8:30/mi + 200m@10:00-12:00/mi) * 4 + 1mi` | An interval session comprising 4 repeats of 400m with 200m recoveries plus a mile of warm-up and cool-down |

More examples can be found in `ExampleWorkoutSchedule.xls`

## Units
### Distance
| Unit | Description | Example |
| ---- | ----------- |---------|
| m | Metre | 400m    |
| km | Kilometre | 5km     |
| mi | Mile | 26.2mi  |

### Pace
A pace is just the time, in minutes and seconds per distance unit, e.g. 9:00/mi is a 9 minute mile; 4:30/km is a 4:30 minute kilometre.

### Pace Range
These are more useful and are used to set a target range for a workout with a minimum and maximum time per distance unit. 
During a workout the watch alerts you if you are too fast or too slow. Examples:

| Pace range | Description |
| ---------- | ----------- |
| 8:00-9:00/mi | between 8 and 9 minutes / mile |
| 4:00-5:00/km | between 4 and 5 minutes per kilometre |

Paces can be given names in the Pace sheet of the workbook. This is optional but can be useful for two reasons:
1. less to type if you regularly use a term like "TEMPO" or "FAST" in your workouts
2. less to update if you want to change the pace for all workouts (e.g. you are getting faster through training and want to increase the pace on all workouts)

### Heart rate ranges
There are two ways to express this, either as beats per minute (bpm) or as a zone (Z1 - Z5). The HR zones are defined inside the watch based on measurements it has made. 
Examples:

| HR range | Description |
| ---------- | ----------- |
| 120-130bpm | between 120 and 130 beats per minute |
| Z3 | Zone 3 |

## Excel workbook
The app expects the input workbook to have specific named sheets and specific column headers within these. Any additional 
sheets or columns are ignored.

| Sheet | Description |
| ---- | ----------- |
| Pace | Named page ranges for use as a shorthand in workout definitions |
| Workout | Named workouts in the workout language described above |
| Schedule | A schedule for workouts for specific dates |

| Sheet | Column | Description |
| ---- | ------ | ----------- |
| Pace | Name  | The name for a pace |
| Pace | Value | The pace defined in workout language |
| Workout | Name | The name for a workout |
| Workout | Description | The workout language definition, may include named paces defined in the pace sheet |
| Schedule | Date | The date for a specific workout |
| Schedule | Workout | Either defined in the workout language or the name a named workout from the Workout sheet |

## ANTLR4 Grammar
The grammar for the language is defined in `grammar/Workout.g4`, which is an ANTLR4 grammar that is used to generate
Java code.

