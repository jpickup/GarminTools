package com.johnpickup.app.excel;

import com.johnpickup.garmin.parser.ScheduledWorkout;
import com.johnpickup.garmin.parser.WorkoutSchedule;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        FileInputStream inputFileStream = new FileInputStream(file);

        if (file.getName().endsWith("xls")) {
            return readXlsStream(inputFileStream);
        }
        else if (file.getName().endsWith("xlsx")) {
            return readXlsxStream(inputFileStream);
        }
        else {
            throw new RuntimeException("Unknown file extension " + file.getName());
        }
    }

    public WorkoutSchedule readXlsStream(InputStream inputStream) throws IOException {
        log.debug("Creating HSSFWorkbook");
        return readWorkbook(new HSSFWorkbook(inputStream));
    }

    public WorkoutSchedule readXlsxStream(InputStream inputStream) throws IOException {
        log.debug("Creating XSSFWorkbook");
        return readWorkbook(new XSSFWorkbook(inputStream));
    }

    public WorkoutSchedule readWorkbook(Workbook wb) throws IOException {
        WorkoutSchedule result = new WorkoutSchedule();
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
