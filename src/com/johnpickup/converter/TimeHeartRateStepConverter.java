package com.johnpickup.converter;

import com.johnpickup.garmin.unit.HeartRateTarget;
import com.johnpickup.garmin.unit.Time;
import com.johnpickup.garmin.workout.TimeHeartRateWorkoutStep;
import com.johnpickup.garmin.workout.WorkoutStep;
import com.johnpickup.parser.Step;
import com.johnpickup.parser.TimeHeartRateStep;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class TimeHeartRateStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        TimeHeartRateStep timeHeartRateStep = (TimeHeartRateStep)step;

        Time t = new Time(timeHeartRateStep.getTime().asDouble() * 60);

        HeartRateTarget heartRateTarget = HeartRateConverterFactory.getInstance()
                .getHeartRateConverter(timeHeartRateStep.getHeartRate())
                .convert(timeHeartRateStep.getHeartRate());

        TimeHeartRateWorkoutStep timeHeartRateWorkoutStep = new TimeHeartRateWorkoutStep(t, heartRateTarget);
        return timeHeartRateWorkoutStep;
    }
}
