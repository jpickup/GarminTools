package com.johnpickup.app;

import com.johnpickup.app.calendar.Schedule;
import com.johnpickup.app.calendar.ScheduleIcalWriter;
import com.johnpickup.app.calendar.WorkoutScheduleConverter;
import com.johnpickup.app.excel.ExcelWorkoutScheduleReader;
import com.johnpickup.garmin.parser.WorkoutSchedule;

import java.io.File;


/**
 * Simple class with command-line interface that takes an Excel definition of a workout schedule and produces
 * in iCal file that can be imported into a calendar application
 */
public class CalendarScheduleGenerator {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CalendarScheduleGenerator.class);

    public static void main(String[] args) {
        try {
            CalendarScheduleGenerator instance = new CalendarScheduleGenerator();
            instance.generate(new File(args[0]), new File("."));
        } catch (Exception e) {
            log.error("Failed to generate iCal schedule", e);
        }
    }

    public void generate(File inputFile, File outputDir) {
        try {
            log.info("Converting {}", inputFile.getPath());
            String strippedName = inputFile.getName();
            if (strippedName.contains(".xls")) {
                strippedName = strippedName.substring(0, strippedName.indexOf(".xls"));
            }
            File outputFile = new File(outputDir, strippedName+".ics");

            log.info("Writing output to {}", outputFile.getPath());
            ExcelWorkoutScheduleReader reader = new ExcelWorkoutScheduleReader();
            log.info("Reading workout schedule");
            WorkoutSchedule workoutSchedule = reader.read(inputFile);

            log.info("Converting workout schedule");
            WorkoutScheduleConverter converter = new WorkoutScheduleConverter();
            Schedule schedule = converter.convert(workoutSchedule);

            log.info("Writing iCal schedule");
            ScheduleIcalWriter icalWriter = new ScheduleIcalWriter(outputFile);
            icalWriter.write(schedule);
        }
        catch (Exception e) {
            log.error("Error converting {}", inputFile.getPath());
            log.error(e.getMessage());
        }
    }
}
