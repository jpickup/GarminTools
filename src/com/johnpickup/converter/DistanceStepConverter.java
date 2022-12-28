package com.johnpickup.converter;

import com.johnpickup.garmin.unit.Distance;
import com.johnpickup.garmin.workout.DistanceWorkoutStep;
import com.johnpickup.garmin.workout.WorkoutStep;
import com.johnpickup.parser.DistanceStep;
import com.johnpickup.parser.Step;

/**
 * Convert independent distance steps into Garmin Workout Distance Steps
 */
public class DistanceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistanceStep distanceStep = (DistanceStep)step;

        Distance d = new Distance(
                distanceStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distanceStep.getDistance().getUnit()));
        DistanceWorkoutStep distanceWorkoutStep = new DistanceWorkoutStep(d);
        return distanceWorkoutStep;
    }
}
