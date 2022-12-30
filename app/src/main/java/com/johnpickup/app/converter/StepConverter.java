package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Created by john on 09/01/2017.
 */
public interface StepConverter {
    WorkoutStep convert(Step step);
}
