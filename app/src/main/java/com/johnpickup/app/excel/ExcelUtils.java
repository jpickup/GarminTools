package com.johnpickup.app.excel;

import com.johnpickup.garmin.parser.Sport;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.Optional;

public class ExcelUtils {
    public static String readStringValue(Row row, int index) {
        if (row.getCell(index) == null || row.getCell(index).getCellType() != CellType.STRING) return null;
        return row.getCell(index).getStringCellValue();
    }

    public static Integer readIntValue(Row row, int index) {
        if (row.getCell(index) == null || row.getCell(index).getCellType() != CellType.NUMERIC) return null;
        return (int) row.getCell(index).getNumericCellValue();
    }

    public static Sport readSportValue(Row row, int index) {
        String sportText = ExcelUtils.readStringValue(row, index);
        return switch (Optional.ofNullable(sportText).map(String::toUpperCase).orElse("")) {
            case "RUNNING", "RUN" -> Sport.RUNNING;
            case "CYCLING", "CYCLE" -> Sport.CYCLING;
            case "SWIMMING", "SWIM" -> Sport.SWIMMING;
            default -> Sport.RUNNING;
        };
    }
}
