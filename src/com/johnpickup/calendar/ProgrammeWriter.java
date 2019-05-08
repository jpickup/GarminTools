package com.johnpickup.calendar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProgrammeWriter {

    private String filename;
    private int rowIdx;

    public ProgrammeWriter(String filename) {
        this.filename = filename;
    }

    public void write(Programme programme) throws IOException {
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(filename);

        Sheet sheet = wb.createSheet("Programme");

        writeHeader(sheet);

        rowIdx = 1;

        List<PlannedWorkout> workouts = new ArrayList<>(programme.getWorkouts());
        Collections.sort(workouts, Comparator.comparingLong(arg0 -> arg0.getOffset()));

        for (PlannedWorkout workout : workouts) {
            writeWorkout(sheet, workout);
            rowIdx++;
        }

        wb.write(fileOut);
        fileOut.close();
    }

    private void writeWorkout(Sheet sheet, PlannedWorkout workout) {
        Row row = sheet.createRow(rowIdx);
        row.createCell(0).setCellValue(workout.getName());
        row.createCell(1).setCellValue(workout.getDescription());
        row.createCell(2).setCellValue(workout.getOffset());
    }

    private void writeHeader(Sheet sheet) {
        Row row = sheet.createRow(rowIdx);
        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Description");
        row.createCell(2).setCellValue("Offset");
    }

}
