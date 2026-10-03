package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.TimeWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.common.unit.Time;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimeStep;

/**
 * Convert a time step (with any target) into a Garmin time workout step
 */
public class TimeStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        TimeStep timeStep = (TimeStep) step;

        Time t = new Time(timeStep.getTime().asDouble() * 60);

        Target target = TargetConverterFactory.getInstance().convert(timeStep.getTarget());

        return new TimeWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), t, target);
    }
}
