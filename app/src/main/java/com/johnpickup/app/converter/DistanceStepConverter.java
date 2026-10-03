package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.DistanceWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.parser.DistanceStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert a distance step (with any target) into a Garmin distance workout step
 */
public class DistanceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistanceStep distanceStep = (DistanceStep) step;

        Distance d = new Distance(
                distanceStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distanceStep.getDistance().getUnit()));

        Target target = TargetConverterFactory.getInstance().convert(distanceStep.getTarget());

        return new DistanceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), d, target);
    }
}
