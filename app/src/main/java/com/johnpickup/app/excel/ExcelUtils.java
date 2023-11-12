package com.johnpickup.app.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtils {
    public static String readStringValue(Row row, int index) {
        if (row.getCell(index) == null || row.getCell(index).getCellType() != CellType.STRING) return null;
        return row.getCell(index).getStringCellValue();
    }

    public static Integer readIntValue(Row row, int index) {
        if (row.getCell(index) == null || row.getCell(index).getCellType() != CellType.NUMERIC) return null;
        return (int) row.getCell(index).getNumericCellValue();
    }
}
