package com.johnpickup.excel;

import com.johnpickup.parser.ScheduledWorkout;
import com.johnpickup.parser.WorkoutSchedule;
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
    private final PaceSheetReader paceReader = new PaceSheetReader();
    private final WorkoutSheetReader workoutReader = new WorkoutSheetReader();
    private final ScheduleSheetReader scheduleReader = new ScheduleSheetReader();

    public WorkoutSchedule read(File file) throws IOException {
        WorkoutSchedule result = new WorkoutSchedule();
        FileInputStream inputFileStream = new FileInputStream(file);

        Workbook wb;
        if (file.getName().endsWith("xls")) {
            wb = new HSSFWorkbook(inputFileStream);
        }
        else if (file.getName().endsWith("xlsx")) {
            try {
                wb = new XSSFWorkbook(inputFileStream);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        else {
            throw new RuntimeException("Unknown file extension " + file.getName());
        }
        result.getPaces().putAll(paceReader.readPaces(wb.getSheet("Pace")));
        result.getWorkouts().putAll(workoutReader.readWorkouts(wb.getSheet("Workout")));
        List<ScheduledWorkout> scheduledWorkouts = scheduleReader.readSchedule(wb.getSheet("Schedule"), result.getWorkouts());
        result.getSchedule().addAll(scheduledWorkouts);

        wb.close();

        return result;
    }
}
