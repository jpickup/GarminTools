package com.johnpickup.app.task;

import com.johnpickup.app.GarminRouteGenerator;
import com.johnpickup.app.javafx.TaskArguments;

public class GpxTask extends UiTask {
    public GpxTask(TaskArguments taskArguments) {
        super(taskArguments);
    }

    @Override
    protected String call() throws Exception {
        updateMessage("Converting " + taskArguments.getInputFile().getPath() + " to FIT file in " + taskArguments.getOutputDir().getPath());
        GarminRouteGenerator generator = new GarminRouteGenerator();
        boolean reverse = taskArguments.getOptions()!=null && taskArguments.getOptions().containsKey("reverse") && ((boolean) taskArguments.getOptions().get("reverse"));
        generator.convert(taskArguments.getInputFile(), taskArguments.getOutputDir(), reverse);
        updateMessage("Completed converting " + taskArguments.getInputFile().getPath());
        return "OK";
    }
}
