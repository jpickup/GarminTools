package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.TimePaceWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.common.unit.Time;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimePaceStep;

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
