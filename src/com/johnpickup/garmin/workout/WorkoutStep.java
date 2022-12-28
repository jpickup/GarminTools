package com.johnpickup.garmin.workout;

import com.garmin.fit.WorkoutStepMesg;

import java.util.List;

public abstract class WorkoutStep {
    private static int stepIndex = 0;

    public static void startNewWorkout() {
        stepIndex = 0;
    }

    protected abstract List<WorkoutStepMesg> generateWorkoutSteps();
    public abstract String getName();
    protected Integer generateWorkoutStepIndex() {
        return stepIndex++;
    }
}
