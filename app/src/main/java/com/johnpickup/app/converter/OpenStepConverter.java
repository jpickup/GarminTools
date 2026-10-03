package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.OpenWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.parser.OpenStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert an open step (with any target) into a Garmin open workout step
 */
public class OpenStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenStep openStep = (OpenStep) step;

        Target target = TargetConverterFactory.getInstance().convert(openStep.getTarget());

        return new OpenWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), target);
    }
}
