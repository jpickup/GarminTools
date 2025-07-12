package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;

import java.util.Collections;
import java.util.List;

/**
 * Simple workout the lasts a specific distance, no pace targets
 */
public class OpenWorkoutStep extends WorkoutStep {
    public OpenWorkoutStep(Intensity intensity) {
        super(intensity);
    }

    @Override
    public String getName() {
        return "Open";
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.OPEN);
        step.setTargetType(WktStepTarget.OPEN);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setTargetValue(0L);
        step.setNotes(nameWithIntensity());

        return Collections.singletonList(step);
    }
}
