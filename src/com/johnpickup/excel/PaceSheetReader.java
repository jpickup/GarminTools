package com.johnpickup.excel;

import com.johnpickup.parser.Pace;
import com.johnpickup.parser.PaceRangeTextParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 10/01/2017.
 */
public class PaceSheetReader {
    private int nameIndex=0;
    private int valueIndex=1;
    private PaceRangeTextParser parser = new PaceRangeTextParser();

    public Map<String, Pace> readPaces(Sheet sheet) throws IOException {
        Map<String, Pace> result = new HashMap<>();

        int rowIdx=0;
        for (Row row : sheet) {
            if (rowIdx++==0) {
                readHeaderRow(row);
            }
            else {
                result.put(readName(row), readPace(row));
            }
        }

        return result;
    }

    private String readName(Row row) {
        return row.getCell(nameIndex).getStringCellValue();
    }

    private Pace readPace(Row row) throws IOException {
        String value = row.getCell(valueIndex).getStringCellValue();
        return parser.parse(value);
    }

    private void readHeaderRow(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Name".equals(cell.getStringCellValue())) nameIndex = cell.getColumnIndex();
            if ("Value".equals(cell.getStringCellValue())) valueIndex = cell.getColumnIndex();
        }
    }
}
