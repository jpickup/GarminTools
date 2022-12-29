package com.johnpickup.excel;

import com.johnpickup.garmin.parser.Workout;
import com.johnpickup.parser.WorkoutTextParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 10/01/2017.
 */
public class WorkoutSheetReader {
    private int nameIndex = 0;
    private int descriptionIndex = 1;
    private final WorkoutTextParser parser = new WorkoutTextParser();

    public Map<String, Workout> readWorkouts(Sheet sheet) throws IOException {
        if (sheet == null) return Collections.emptyMap();

        Map<String, Workout> result = new HashMap<>();

        int rowIdx=0;
        for (Row row : sheet) {
            if (rowIdx++==0) {
                readHeaderRow(row);
            }
            else {
                result.put(readName(row), readWorkout(row));
            }
        }

        return result;
    }

    private String readName(Row row) {
        return row.getCell(nameIndex).getStringCellValue();
    }

    private Workout readWorkout(Row row) throws IOException {
        String description = row.getCell(descriptionIndex).getStringCellValue();
        return parser.parse(description);
    }

    private void readHeaderRow(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Name".equals(cell.getStringCellValue())) nameIndex = cell.getColumnIndex();
            if ("Description".equals(cell.getStringCellValue())) descriptionIndex = cell.getColumnIndex();
        }
    }
}
