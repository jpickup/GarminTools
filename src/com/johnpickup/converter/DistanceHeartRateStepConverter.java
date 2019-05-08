package com.johnpickup.converter;

import com.johnpickup.garmin.unit.Distance;
import com.johnpickup.garmin.unit.HeartRateTarget;
import com.johnpickup.garmin.workout.DistanceHeartRateWorkoutStep;
import com.johnpickup.garmin.workout.WorkoutStep;
import com.johnpickup.parser.DistanceHeartRateStep;
import com.johnpickup.parser.Step;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class DistanceHeartRateStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistanceHeartRateStep distanceHeartRateStep = (DistanceHeartRateStep)step;

        Distance d = new Distance(
                distanceHeartRateStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distanceHeartRateStep.getDistance().getUnit()));

        HeartRateTarget heartRateTarget = HeartRateConverterFactory.getInstance()
                .getHeartRateConverter(distanceHeartRateStep.getHeartRate())
                .convert(distanceHeartRateStep.getHeartRate());

        DistanceHeartRateWorkoutStep distanceHeartRateWorkoutStep = new DistanceHeartRateWorkoutStep(d, heartRateTarget);
        return distanceHeartRateWorkoutStep;
    }
}
