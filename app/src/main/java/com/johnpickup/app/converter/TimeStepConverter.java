package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.TimeWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Time;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimeStep;

/**
 * Convert independent distance steps into Garmin Workout Distance Steps
 */
public class TimeStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        TimeStep timeStep = (TimeStep)step;

        Time t = new Time(timeStep.getTime().asDouble() * 60);
        TimeWorkoutStep timeWorkoutStep = new TimeWorkoutStep(t);
        return timeWorkoutStep;
    }
}
