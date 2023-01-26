package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.OpenWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent distance steps into Garmin Workout Distance Steps
 */
public class OpenStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenWorkoutStep openWorkoutStep = new OpenWorkoutStep();
        return openWorkoutStep;
    }
}
