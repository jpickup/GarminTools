package com.johnpickup.app.javafx;

import com.johnpickup.app.task.ScheduleFitTask;
import javafx.stage.FileChooser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ConversionType {
    GPX_TO_FIT("GPX to FIT", null, null),
    SCHEDULE_TO_FIT("Excel schedule to FIT", ScheduleFitTask.class,
            List.of(new FileChooser.ExtensionFilter("Excel Workbook", "*.xlsx", "*.xls"))),
    SCHEDULE_TO_ICAL("Excel schedule to iCal", null, null);
    final String description;
    final Class task;
    final List<FileChooser.ExtensionFilter> filePattern;
    @Override
    public String toString() {
        return description;
    }
}
