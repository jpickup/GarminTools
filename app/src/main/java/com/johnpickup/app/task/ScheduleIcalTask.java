package com.johnpickup.app.task;

import com.johnpickup.app.CalendarScheduleGenerator;
import com.johnpickup.app.javafx.TaskArguments;

public class ScheduleIcalTask extends UiTask {
    public ScheduleIcalTask(TaskArguments taskArguments) {
        super(taskArguments);
    }

    @Override
    protected String call() throws Exception {
        updateMessage("Converting " + taskArguments.getInputFile().getPath() + " to iCal file in " + taskArguments.getOutputDir().getPath());
        CalendarScheduleGenerator generator = new CalendarScheduleGenerator();
        generator.generate(taskArguments.getInputFile(), taskArguments.getOutputDir());
        return "OK";
    }
}
