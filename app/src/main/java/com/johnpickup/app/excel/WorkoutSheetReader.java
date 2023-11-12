package com.johnpickup.app.excel;

import com.johnpickup.app.parser.WorkoutTextParser;
import com.johnpickup.garmin.parser.Workout;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by john on 10/01/2017.
 */
public class WorkoutSheetReader {
    private int nameIndex = 0;
    private int descriptionIndex = 1;
    private final WorkoutTextParser parser = new WorkoutTextParser();

    public Map<String, Workout> readWorkouts(Sheet sheet) {
        if (sheet == null) return Collections.emptyMap();

        Map<String, Workout> result = new HashMap<>();

        int rowIdx=0;
        for (Row row : sheet) {
            if (rowIdx++==0) {
                readHeaderRow(row);
            }
            else {
                String name = readName(row);
                Workout workout = readWorkout(row);
                if (name != null && workout != null) {
                    result.put(name, workout);
                }
            }
        }

        return result;
    }

    private String readName(Row row) {
        return ExcelUtils.readStringValue(row, nameIndex);
    }

    private Workout readWorkout(Row row) {
        return Optional.ofNullable(ExcelUtils.readStringValue(row, descriptionIndex))
                .map(parser::parse)
                .orElse(null);
    }

    private void readHeaderRow(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Name".equals(cell.getStringCellValue())) nameIndex = cell.getColumnIndex();
            if ("Description".equals(cell.getStringCellValue())) descriptionIndex = cell.getColumnIndex();
        }
    }
}
