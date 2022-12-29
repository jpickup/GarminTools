package com.johnpickup;

import com.johnpickup.converter.WorkoutScheduleConverter;
import com.johnpickup.excel.ExcelWorkoutScheduleReader;
import com.johnpickup.garmin.WorkoutSaver;
import com.johnpickup.garmin.parser.WorkoutSchedule;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;


/**
 * Simple class with command-line interface that takes an Excel definition of a workout schedule and produces a set
 * of FIT files for loading onto the Garmin device
 */
@Slf4j
public class GarminScheduleGenerator {
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
            for (com.johnpickup.garmin.workout.Workout garminWorkout : converter.getGarminWorkouts()) {
                String workoutFilename = generateWorkoutFilename(garminWorkout);
                File workoutFile = new File(outputDir, workoutFilename);
                log.debug("Saving workout {} as {}", garminWorkout.getName(), workoutFile.getPath());
                workoutSaver.save(garminWorkout, workoutFile);
                log.info("Saved workout {} as {}", garminWorkout.getName(), workoutFile.getPath());
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

    private String generateWorkoutFilename(com.johnpickup.garmin.workout.Workout garminWorkout) {
        String name = garminWorkout.getName();
        return name.trim().replaceAll(" ","").replaceAll("/","")
                .replaceAll("\\(","").replaceAll("\\)","")
                .replaceAll(":","").replaceAll("\\+","-")
                .replaceAll("\\*","x")+ ".fit";
    }
}
