package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.OpenPaceWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.parser.OpenPaceStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class OpenPaceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenPaceStep distancePaceStep = (OpenPaceStep)step;

        PaceTarget p = PaceConverterFactory.getInstance().getPaceConverter(distancePaceStep.getPace()).convert(distancePaceStep.getPace());
        OpenPaceWorkoutStep openPaceWorkoutStep = new OpenPaceWorkoutStep(p);
        return openPaceWorkoutStep;
    }
}
