package com.johnpickup.converter;

import com.johnpickup.garmin.unit.Time;
import com.johnpickup.garmin.workout.TimeWorkoutStep;
import com.johnpickup.garmin.workout.WorkoutStep;
import com.johnpickup.parser.Step;
import com.johnpickup.parser.TimeStep;

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
