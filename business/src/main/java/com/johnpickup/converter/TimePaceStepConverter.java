package com.johnpickup.converter;

import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimePaceStep;
import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.garmin.unit.Time;
import com.johnpickup.garmin.workout.TimePaceWorkoutStep;
import com.johnpickup.garmin.workout.WorkoutStep;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class TimePaceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        TimePaceStep timePaceStep = (TimePaceStep)step;

        Time t = new Time(timePaceStep.getTime().asDouble() * 60);
        PaceTarget p = PaceConverterFactory.getInstance().getPaceConverter(timePaceStep.getPace()).convert(timePaceStep.getPace());
        TimePaceWorkoutStep timePaceWorkoutStep = new TimePaceWorkoutStep(t, p);
        return timePaceWorkoutStep;
    }
}
