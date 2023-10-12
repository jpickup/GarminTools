package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.TimeHeartRateWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.HeartRateTarget;
import com.johnpickup.garmin.common.unit.Time;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.TimeHeartRateStep;

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

        TimeHeartRateWorkoutStep timeHeartRateWorkoutStep =
                new TimeHeartRateWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), t, heartRateTarget);
        return timeHeartRateWorkoutStep;
    }
}
