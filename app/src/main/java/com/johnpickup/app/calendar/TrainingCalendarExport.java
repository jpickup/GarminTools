package com.johnpickup.app.calendar;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TrainingCalendarExport {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TrainingCalendarExport.class);
    private static final DateTimeFormatter ddmmyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        try {
            String inputFilename = args[0];
            String outputFilename = args[1];
            LocalDate raceDate = LocalDate.parse(args[2], ddmmyyyy);
            ProgrammeReader reader = new ProgrammeReader(inputFilename);
            Programme programme = reader.read();
            Schedule schedule = ScheduleGenerator.generate(programme, raceDate);
            System.out.println(schedule);

            ScheduleWriter writer;
            if (outputFilename.endsWith(".xls")) {
                writer = new ScheduleExcelWriter(outputFilename);
            } else if (outputFilename.endsWith(".ics")) {
                writer = new ScheduleIcalWriter(new File(outputFilename));
            } else {
                throw new RuntimeException("Unsupported file type " + outputFilename);
            }

            writer.write(schedule);
        } catch (Exception e) {
            log.error("ERROR", e);
            throw new RuntimeException(e);
        }
    }
}
