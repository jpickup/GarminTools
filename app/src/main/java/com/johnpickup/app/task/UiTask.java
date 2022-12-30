package com.johnpickup.app.task;

import com.johnpickup.app.javafx.TaskArguments;
import javafx.concurrent.Task;

public abstract class UiTask extends Task<String> {
    protected final TaskArguments taskArguments;

    protected UiTask(TaskArguments taskArguments) {
        this.taskArguments = taskArguments;
    }
}
