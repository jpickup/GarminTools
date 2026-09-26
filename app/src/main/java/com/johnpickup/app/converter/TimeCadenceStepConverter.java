package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.TimeCadenceWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.CadenceTarget;
import com.johnpickup.garmin.common.unit.Time;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimeCadenceStep;

/**
 * Convert independent cadence steps into the Garmin equivalent
 */
public class TimeCadenceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        TimeCadenceStep timeCadenceStep = (TimeCadenceStep)step;

        Time t = new Time(timeCadenceStep.getTime().asDouble() * 60);

        CadenceTarget cadenceTarget = CadenceConverterFactory.getInstance()
                .getCadenceConverter(timeCadenceStep.getCadence())
                .convert(timeCadenceStep.getCadence());

        return new TimeCadenceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), t, cadenceTarget);
    }
}
