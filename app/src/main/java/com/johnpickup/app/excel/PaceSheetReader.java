package com.johnpickup.app.excel;

import com.johnpickup.app.parser.PaceRangeTextParser;
import com.johnpickup.garmin.parser.Pace;
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
public class PaceSheetReader {
    private int nameIndex=0;
    private int valueIndex=1;
    private final PaceRangeTextParser parser = new PaceRangeTextParser();

    public Map<String, Pace> readPaces(Sheet sheet) {
        if (sheet == null) return Collections.emptyMap();
        Map<String, Pace> result = new HashMap<>();

        int rowIdx=0;
        for (Row row : sheet) {
            if (rowIdx++==0) {
                readHeaderRow(row);
            }
            else {
                String name = readName(row);
                Pace pace = readPace(row);
                if (name != null && pace != null) {
                    result.put(name, pace);
                }
            }
        }

        return result;
    }

    private String readName(Row row) {
        return ExcelUtils.readStringValue(row, nameIndex);
    }

    private Pace readPace(Row row) {
        return Optional.ofNullable(ExcelUtils.readStringValue(row, valueIndex))
                .map(parser::parse)
                .orElse(null);
    }

    private void readHeaderRow(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Name".equals(cell.getStringCellValue())) nameIndex = cell.getColumnIndex();
            if ("Value".equals(cell.getStringCellValue())) valueIndex = cell.getColumnIndex();
        }
    }
}
