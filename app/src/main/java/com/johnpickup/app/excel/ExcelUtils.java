package com.johnpickup.app.excel;

import com.johnpickup.garmin.parser.Sport;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.Optional;

public class ExcelUtils {
    public static String readStringValue(Row row, Integer index) {
        if (index == null || row.getCell(index) == null || row.getCell(index).getCellType() != CellType.STRING) return null;
        return row.getCell(index).getStringCellValue();
    }

    public static Integer readIntValue(Row row, Integer index) {
        if (index == null || row.getCell(index) == null || row.getCell(index).getCellType() != CellType.NUMERIC) return null;
        return (int) row.getCell(index).getNumericCellValue();
    }

    public static Sport readSportValue(Row row, Integer index) {
        String sportText = ExcelUtils.readStringValue(row, index);
        return switch (Optional.ofNullable(sportText).map(String::toUpperCase).orElse("")) {
            case "RUNNING", "RUN" -> Sport.RUNNING;
            case "ROAD RUNNING" -> Sport.ROAD_RUNNING;
            case "TRAIL RUNNING" -> Sport.TRAIL_RUNNING;
            case "CYCLING", "CYCLE" -> Sport.CYCLING;
            case "ROAD CYCLING" -> Sport.ROAD_CYCLING;
            case "MTB", "MOUNTAIN BIKING" -> Sport.MTB;
            case "SWIMMING", "SWIM" -> Sport.SWIMMING;
            case "POOL SWIMMING", "POOL" -> Sport.POOL_SWIMMING;
            case "OPEN WATER SWIMMING", "OPEN WATER" -> Sport.OPEN_WATER_SWIMMING;
            default -> Sport.RUNNING;
        };
    }
}
