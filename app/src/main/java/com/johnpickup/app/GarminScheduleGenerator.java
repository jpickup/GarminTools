package com.johnpickup.app;

import com.johnpickup.app.converter.WorkoutScheduleConverter;
import com.johnpickup.app.excel.ExcelWorkoutScheduleReader;
import com.johnpickup.app.garmin.WorkoutSaver;
import com.johnpickup.app.garmin.workout.Workout;
import com.johnpickup.garmin.parser.WorkoutSchedule;

import java.io.File;
import java.io.IOException;

/**
 * Simple class with command-line interface that takes an Excel definition of a workout schedule and produces a set
 * of FIT files for loading onto the Garmin device
 */
public class GarminScheduleGenerator {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GarminScheduleGenerator.class);
    public static void main(String[] args) {
        try {
            GarminScheduleGenerator instance = new GarminScheduleGenerator();
            instance.generate(new File(args[0]), new File("."));
        } catch (Exception e) {
            log.error("Failed to generate workout schedule", e);
        }
    }

    public void generate(File inputFile, File outputDir) throws IOException {
        try {
            log.info("Converting {}", inputFile.getPath());
            log.info("Writing output to {}", outputDir.getPath());
            ExcelWorkoutScheduleReader reader = new ExcelWorkoutScheduleReader();
            WorkoutScheduleConverter converter = new WorkoutScheduleConverter();
            WorkoutSaver workoutSaver = new WorkoutSaver();
            log.info("Reading workout schedule");
            WorkoutSchedule workoutSchedule = reader.read(inputFile);

            log.info("Converting workout schedule");
            converter.convert(workoutSchedule);

            log.info("Saving workouts");
            for (Workout garminWorkout : converter.getGarminWorkouts()) {
                String workoutFilename = generateWorkoutFilename(garminWorkout);
                File workoutFile = new File(outputDir, workoutFilename);
                log.debug("Saving workout {} as {}", garminWorkout, workoutFile.getPath());
                workoutSaver.save(garminWorkout, workoutFile);
                log.info("Saved workout {} as {}", garminWorkout, workoutFile.getPath());
            }

            String scheduleFilename = "schedule.fit";
            File scheduleFile = new File(outputDir, scheduleFilename);
            log.info("Saving schedule as {}", scheduleFile.getPath());
            workoutSaver.save(converter.getTrainingSchedule(), scheduleFile);
            log.info("Saved workout schedule as {}", scheduleFile.getPath());
        }
        catch (RuntimeException e) {
            log.error("Error converting {}", inputFile.getPath());
            log.error(e.getMessage());
        }
    }

    private String generateWorkoutFilename(Workout garminWorkout) {
        String name = garminWorkout.getName();
        return name.trim().replaceAll(" ","").replaceAll("/","")
                .replaceAll("\\(","").replaceAll("\\)","")
                .replaceAll(":","").replaceAll("\\+","-")
                .replaceAll("\\*","x")+ ".fit";
    }
}
