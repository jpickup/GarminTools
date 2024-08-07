package com.johnpickup.app.excel;

import com.johnpickup.app.parser.WorkoutTextParser;
import com.johnpickup.garmin.parser.ScheduledWorkout;
import com.johnpickup.garmin.parser.Workout;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by john on 10/01/2017.
 */
public class ScheduleSheetReader {
    private int dateIndex = 0;
    private int workoutIndex = 1;
    private final WorkoutTextParser parser = new WorkoutTextParser();


    public List<ScheduledWorkout> readSchedule(Sheet sheet, Map<String, Workout> workouts) {
        if (sheet == null) return Collections.emptyList();

        List<ScheduledWorkout> result = new ArrayList<>();

        int rowIdx=0;
        for (Row row : sheet) {
            if (rowIdx++==0) {
                readHeaderRow(row);
            }
            else {
                Optional.ofNullable(readScheduledWorkout(row, workouts)).ifPresent(result::add);
            }
        }

        return result;
    }

    private ScheduledWorkout readScheduledWorkout(Row row, Map<String, Workout> workouts) {
        Cell dateCell = row.getCell(dateIndex);
        Cell workoutCell = row.getCell(workoutIndex);
        if (dateCell != null && workoutCell != null) {
            Date date = dateCell.getDateCellValue();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String value = workoutCell.getStringCellValue();
            Workout workout;
            if (workouts.containsKey(value)) {
                workout = workouts.get(value);
            } else {
                workout = parser.parse(value);
                workouts.put(value, workout);
            }

            return new ScheduledWorkout(localDate, workout, value, workout.toString());
        }
        else {
            return null;
        }

    }

    private void readHeaderRow(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Date".equals(cell.getStringCellValue())) dateIndex = cell.getColumnIndex();
            if ("Workout".equals(cell.getStringCellValue())) workoutIndex = cell.getColumnIndex();
        }
    }
}
