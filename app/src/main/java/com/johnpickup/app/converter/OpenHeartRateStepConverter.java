package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.OpenHeartRateWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.HeartRateTarget;
import com.johnpickup.garmin.parser.OpenHeartRateStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class OpenHeartRateStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        OpenHeartRateStep openHeartRateStep = (OpenHeartRateStep)step;

        HeartRateTarget heartRateTarget = HeartRateConverterFactory.getInstance()
                .getHeartRateConverter(openHeartRateStep.getHeartRate())
                .convert(openHeartRateStep.getHeartRate());

        OpenHeartRateWorkoutStep openHeartRateWorkoutStep = new OpenHeartRateWorkoutStep(heartRateTarget);
        return openHeartRateWorkoutStep;
    }
}
