package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.DistanceCadenceWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.common.unit.CadenceTarget;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.parser.DistanceCadenceStep;
import com.johnpickup.garmin.parser.Step;

/**
 * Convert independent cadence steps into the Garmin equivalent
 */
public class DistanceCadenceStepConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        DistanceCadenceStep distanceCadenceStep = (DistanceCadenceStep)step;

        Distance d = new Distance(
                distanceCadenceStep.getDistance().getQuantity(),
                DiatanceUnitConverter.convert(distanceCadenceStep.getDistance().getUnit()));

        CadenceTarget cadenceTarget = CadenceConverterFactory.getInstance()
                .getCadenceConverter(distanceCadenceStep.getCadence())
                .convert(distanceCadenceStep.getCadence());

        return new DistanceCadenceWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), d, cadenceTarget);
    }
}
