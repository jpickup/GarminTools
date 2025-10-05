package com.johnpickup.app.excel;

import com.johnpickup.app.parser.WorkoutTextParser;
import com.johnpickup.garmin.parser.ScheduledWorkout;
import com.johnpickup.garmin.parser.WorkoutSchedule;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


/**
 * Class to read all the content of a Workout Schedule Excel workbook
 */
public class ExcelWorkoutScheduleReader {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ExcelWorkoutScheduleReader.class);
    private final PaceSheetReader paceReader = new PaceSheetReader();
    private final WorkoutSheetReader workoutReader = new WorkoutSheetReader();
    private final ScheduleSheetReader scheduleReader = new ScheduleSheetReader();

    public WorkoutSchedule read(File file) throws IOException {
        log.debug("Loading {}", file.getAbsolutePath());
        WorkoutSchedule result = new WorkoutSchedule();
        FileInputStream inputFileStream = new FileInputStream(file);

        Workbook wb;
        if (file.getName().endsWith("xls")) {
            log.debug("Creating HSSFWorkbook");
            wb = new HSSFWorkbook(inputFileStream);
        }
        else if (file.getName().endsWith("xlsx")) {
            try {
                log.debug("Creating XSSFWorkbook");
                wb = new XSSFWorkbook(inputFileStream);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        else {
            throw new RuntimeException("Unknown file extension " + file.getName());
        }
        log.debug("Reading paces");
        result.getPaces().putAll(paceReader.readPaces(wb.getSheet("Pace")));
        log.debug("Reading workouts");
        result.getWorkouts().putAll(workoutReader.readWorkouts(wb.getSheet("Workout")));
        log.debug("Reading schedule");
        List<ScheduledWorkout> scheduledWorkouts = scheduleReader.readSchedule(wb.getSheet("Schedule"), result.getWorkouts());
        log.debug("Done reading input file");
        result.getSchedule().addAll(scheduledWorkouts);

        wb.close();

        return result;
    }
}
