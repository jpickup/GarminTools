package com.johnpickup.app.calendar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ProgrammeReader {

    private String filename;
    private int rowIdx;
    private int nameIndex = -1;
    private int descriptionIndex = -1;
    private int offsetIndex = -1;

    public ProgrammeReader(String filename) {
        this.filename = filename;
    }

    public Programme read() throws IOException {
        Programme result = new Programme();
        FileInputStream inputFile = new FileInputStream(filename);

        Workbook wb = new HSSFWorkbook(inputFile);

        Sheet sheet = wb.getSheet("Schedule");

        rowIdx = 0;

        for (Row row : sheet) {
            if (rowIdx++ == 0) {
                readHeader(row);
            } else {
                PlannedWorkout workout = readWorkout(row);
                result.addWorkout(workout);
            }
        }
        wb.close();

        return result;
    }

    private PlannedWorkout readWorkout(Row row) {
        String name = row.getCell(nameIndex).getStringCellValue();
        String description = row.getCell(descriptionIndex).getStringCellValue();
        int offset = (int) row.getCell(offsetIndex).getNumericCellValue();
        return new PlannedWorkout(name, description, offset);
    }

    private void readHeader(Row row) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;

            if ("Workout".equals(cell.getStringCellValue())) nameIndex = cell.getColumnIndex();
            if ("Description".equals(cell.getStringCellValue())) descriptionIndex = cell.getColumnIndex();
            if ("Offset".equals(cell.getStringCellValue())) offsetIndex = cell.getColumnIndex();
        }
    }

}
