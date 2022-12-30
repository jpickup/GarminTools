package com.johnpickup.app.javafx;

import com.johnpickup.app.task.GpxTask;
import com.johnpickup.app.task.ScheduleFitTask;
import com.johnpickup.app.task.ScheduleIcalTask;
import javafx.stage.FileChooser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ConversionType {
    GPX_TO_FIT("GPX to FIT", GpxTask.class,
            List.of(new FileChooser.ExtensionFilter("GPX", "*.gpx"))),
    SCHEDULE_TO_FIT("Excel Schedule to FIT", ScheduleFitTask.class,
            List.of(new FileChooser.ExtensionFilter("Excel Workbook", "*.xlsx", "*.xls"))),
    SCHEDULE_TO_ICAL("Excel Schedule to iCal", ScheduleIcalTask.class,
            List.of(new FileChooser.ExtensionFilter("Excel Workbook", "*.xlsx", "*.xls")));
    final String description;
    final Class task;
    final List<FileChooser.ExtensionFilter> filePattern;
    @Override
    public String toString() {
        return description;
    }
}
