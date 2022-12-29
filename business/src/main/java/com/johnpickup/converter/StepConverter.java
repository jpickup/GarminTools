package com.johnpickup.converter;

import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.workout.WorkoutStep;

/**
 * Created by john on 09/01/2017.
 */
public interface StepConverter {
    WorkoutStep convert(Step step);
}
