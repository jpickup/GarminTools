package com.johnpickup.app.task;

import com.johnpickup.app.GarminScheduleGenerator;
import com.johnpickup.app.javafx.TaskArguments;

public class ScheduleFitTask extends UiTask {
    public ScheduleFitTask(TaskArguments taskArguments) {
        super(taskArguments);
    }

    @Override
    protected String call() throws Exception {
        updateMessage("Converting " + taskArguments.getInputFile().getPath() + " to FIT files in " + taskArguments.getOutputDir().getPath());
        GarminScheduleGenerator generator = new GarminScheduleGenerator();
        generator.generate(taskArguments.getInputFile(), taskArguments.getOutputDir());
        return "OK";
    }
}
