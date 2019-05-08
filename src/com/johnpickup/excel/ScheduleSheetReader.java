package com.johnpickup.excel;

import com.johnpickup.parser.ScheduledWorkout;
import com.johnpickup.parser.Workout;
import com.johnpickup.parser.WorkoutTextParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 10/01/2017.
 */
public class ScheduleSheetReader {
    private int dateIndeex =0;
    private int workoutIndex =1;
    private WorkoutTextParser parser = new WorkoutTextParser();


    public List<ScheduledWorkout> readSchedule(Sheet sheet, Map<String, Workout> workouts) throws IOException {
        List<ScheduledWorkout> result = new ArrayList<>();

        int rowIdx=0;
        for (Row row : sheet) {
            if (rowIdx++==0) {
                readHeaderRow(row);
            }
            else {
                result.add(readScheduledWorkout(row, workouts));
            }
        }

        return result;
    }

    private ScheduledWorkout readScheduledWorkout(Row row, Map<String, Workout> workouts) throws IOException {
        Date date = row.getCell(dateIndeex).getDateCellValue();
        String value = row.getCell(workoutIndex).getStringCellValue();
        Workout workout;
        if (workouts.containsKey(value)) {
            workout = workouts.get(value);
        }
        else {
            workout = parser.parse(value);
            workouts.put(value, workout);
        }

        return new ScheduledWorkout(date, workout, value, workout.toString());
    }

    private void readHeaderRow(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Date".equals(cell.getStringCellValue())) dateIndeex = cell.getColumnIndex();
            if ("Workout".equals(cell.getStringCellValue())) workoutIndex = cell.getColumnIndex();
        }
    }
}
