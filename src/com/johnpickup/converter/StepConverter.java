package com.johnpickup.converter;

import com.johnpickup.garmin.workout.WorkoutStep;
import com.johnpickup.parser.Step;

/**
 * Created by john on 09/01/2017.
 */
public interface StepConverter {
    WorkoutStep convert(Step step);
}
