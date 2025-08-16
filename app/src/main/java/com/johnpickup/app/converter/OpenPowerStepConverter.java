package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.OpenPowerWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.parser.OpenPowerStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class OpenPowerStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenPowerStep openPowerStep = (OpenPowerStep)step;

        PowerTarget powerTarget = PowerConverterFactory.getInstance()
                .getPowerConverter(openPowerStep.getPower())
                .convert(openPowerStep.getPower());

        return new OpenPowerWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), powerTarget);
    }
}
