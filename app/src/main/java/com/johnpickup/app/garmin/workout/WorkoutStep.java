package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WorkoutStepMesg;

import java.util.List;
import java.util.Optional;

public abstract class WorkoutStep {
    private static int stepIndex = 0;

    public WorkoutStep(Intensity intensity) {
        this.intensity = intensity;
    }

    protected final Intensity intensity;

    public static void startNewWorkout() {
        stepIndex = 0;
    }

    protected abstract List<WorkoutStepMesg> generateWorkoutSteps();
    public abstract String getName();
    protected Integer generateWorkoutStepIndex() {
        return stepIndex++;
    }
    protected String intensityDescription() {
        return Optional.ofNullable(intensity).map(Enum::toString).orElse("");
    }
    protected String nameWithIntensity() {
        return intensity == null ? getName() :  getName() + " | " + intensityDescription();
    }
}
