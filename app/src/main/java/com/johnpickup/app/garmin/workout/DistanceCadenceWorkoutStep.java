package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.CadenceTarget;
import com.johnpickup.garmin.common.unit.Distance;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a cadence target
 */
public class DistanceCadenceWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final CadenceTarget cadenceTarget;

    public DistanceCadenceWorkoutStep(Intensity intensity, Distance distance, CadenceTarget cadenceTarget) {
        super(intensity);
        this.distance = distance;
        this.cadenceTarget = cadenceTarget;
    }

    @Override
    public String getName() {
        return distance.toString() + " " + cadenceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.CADENCE);
        step.setTargetValue(cadenceTarget.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(cadenceTarget.getGarminLow());
        step.setCustomTargetValueHigh(cadenceTarget.getGarminHigh());
        step.setNotes(nameWithIntensity());
        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceCadenceWorkoutStep that = (DistanceCadenceWorkoutStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(cadenceTarget, that.cadenceTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, cadenceTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceCadenceWorkoutStep;
    }
}
