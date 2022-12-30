package com.johnpickup.app.calendar;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class ScheduleIcalWriter implements ScheduleWriter {
    private File outputFile;
    private ICalendar iCalendar;

    public ScheduleIcalWriter(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void write(Schedule schedule) throws Exception {
        iCalendar = new ICalendar();

        List<ScheduledWorkout> workouts = new ArrayList<>(schedule.getWorkouts());
        workouts.sort(Comparator.comparing(ScheduledWorkout::getDate));

        for (ScheduledWorkout workout : workouts) {
            log.info(workout.getName());
            writeWorkout(workout);
        }
        log.info("Done creating iCal");

        String iCalStr = Biweekly.write(iCalendar).go();

        FileWriter writer = new FileWriter(outputFile);
        writer.write(iCalStr);
        writer.close();

        log.info("Done writing iCal to {}", outputFile.getAbsolutePath());
    }

    private void writeWorkout(ScheduledWorkout workout) {
        LocalDate workoutDate = workout.getDate();
        VEvent event = new VEvent();
        Summary summary = event.setSummary(workout.getName());
        summary.setLanguage("en-uk");
        java.util.Date date = java.util.Date.from(workoutDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        event.setDateStart(date, false);
        //Duration duration = new Duration.Builder().days(1).build();
        //event.setDuration(duration);
        event.setDescription(workout.getDescription());

        iCalendar.addEvent(event);
    }

}
