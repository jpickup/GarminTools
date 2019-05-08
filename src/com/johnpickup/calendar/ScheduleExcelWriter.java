package com.johnpickup.calendar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScheduleExcelWriter implements ScheduleWriter {

    private String filename;
    private int rowIdx;
    private CellStyle dateStyle;

    public ScheduleExcelWriter(String filename) {
        this.filename = filename;
    }

    public void write(Schedule schedule) throws IOException {
        Workbook wb = new HSSFWorkbook();
        initialiseWorkbook(wb);

        FileOutputStream fileOut = new FileOutputStream(filename);

        Sheet sheet = wb.createSheet("Programme");
        writeHeader(sheet);

        rowIdx = 1;

        List<ScheduledWorkout> workouts = new ArrayList<>(schedule.getWorkouts());
        workouts.sort(Comparator.comparing(ScheduledWorkout::getDate));

        for (ScheduledWorkout workout : workouts) {
            writeWorkout(sheet, workout);
            rowIdx++;
        }

        wb.write(fileOut);
        fileOut.close();
    }

    private void initialiseWorkbook(Workbook workbook) {
        CreationHelper createHelper = workbook.getCreationHelper();
        dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    }

    private void writeWorkout(Sheet sheet, ScheduledWorkout workout) {
        Row row = sheet.createRow(rowIdx);
        row.createCell(0).setCellValue(workout.getName());
        row.createCell(1).setCellValue(workout.getDescription());

        java.util.Date dateVal = java.util.Date.from(workout.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Cell cell = row.createCell(2);
        cell.setCellValue(dateVal);
        cell.setCellStyle(dateStyle);
    }

    private void writeHeader(Sheet sheet) {
        Row row = sheet.createRow(rowIdx);
        row.createCell(0).setCellValue("Name");
        row.createCell(1).setCellValue("Description");
        row.createCell(2).setCellValue("Date");
    }

}
