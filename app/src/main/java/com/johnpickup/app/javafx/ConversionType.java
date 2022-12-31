package com.johnpickup.app.javafx;

import com.johnpickup.app.task.GpxTask;
import com.johnpickup.app.task.ScheduleFitTask;
import com.johnpickup.app.task.ScheduleIcalTask;
import javafx.stage.FileChooser;

import java.util.List;

public enum ConversionType {
    GPX_TO_FIT("GPX to FIT", GpxTask.class,
            List.of(new FileChooser.ExtensionFilter("GPX", "*.gpx"))),
    SCHEDULE_TO_FIT("Excel Schedule to FIT", ScheduleFitTask.class,
            List.of(new FileChooser.ExtensionFilter("Excel Workbook", "*.xlsx", "*.xls"))),
    SCHEDULE_TO_ICAL("Excel Schedule to iCal", ScheduleIcalTask.class,
            List.of(new FileChooser.ExtensionFilter("Excel Workbook", "*.xlsx", "*.xls")));
    private final String description;

    public Class getTask() {
        return task;
    }

    private final Class task;
    private final List<FileChooser.ExtensionFilter> filePattern;

    private ConversionType(String description, Class task, List<FileChooser.ExtensionFilter> filePattern) {
        this.description = description;
        this.task = task;
        this.filePattern = filePattern;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return this.description;
    }

    public List<FileChooser.ExtensionFilter> getFilePattern() {
        return this.filePattern;
    }
}
