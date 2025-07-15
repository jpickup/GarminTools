package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.PowerTarget;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a heart rate target
 */
public class DistancePowerWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final PowerTarget powerTarget;

    public DistancePowerWorkoutStep(Intensity intensity, Distance distance, PowerTarget powerTarget) {
        super(intensity);
        this.distance = distance;
        this.powerTarget = powerTarget;
    }

    @Override
    public String getName() {
        return distance.toString() + " " + powerTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.POWER);
        step.setTargetValue(powerTarget.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(powerTarget.getGarminLow());
        step.setCustomTargetValueHigh(powerTarget.getGarminHigh());
        step.setNotes(nameWithIntensity());

        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistancePowerWorkoutStep that = (DistancePowerWorkoutStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(powerTarget, that.powerTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, powerTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistancePowerWorkoutStep;
    }

}
