package com.johnpickup.converter;

import com.johnpickup.garmin.parser.DistancePaceStep;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.unit.Distance;
import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.garmin.workout.DistancePaceWorkoutStep;
import com.johnpickup.garmin.workout.WorkoutStep;

/**
 * Convert independent pace steps into the Garmin equivalent
 */
public class DistancePaceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistancePaceStep distancePaceStep = (DistancePaceStep)step;

        Distance d = new Distance(
                distancePaceStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distancePaceStep.getDistance().getUnit()));
        PaceTarget p = PaceConverterFactory.getInstance().getPaceConverter(distancePaceStep.getPace()).convert(distancePaceStep.getPace());
        DistancePaceWorkoutStep distancePaceWorkoutStep = new DistancePaceWorkoutStep(d, p);
        return distancePaceWorkoutStep;
    }
}
