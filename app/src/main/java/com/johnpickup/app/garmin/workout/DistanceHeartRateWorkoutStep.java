package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.HeartRateTarget;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a heart rate target
 */
public class DistanceHeartRateWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final HeartRateTarget heartRateTarget;

    public DistanceHeartRateWorkoutStep(Distance distance, HeartRateTarget heartRateTarget) {
        this.distance = distance;
        this.heartRateTarget = heartRateTarget;
    }

    @Override
    public String getName() {
        return distance.toString() + " " + heartRateTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.HEART_RATE);
        step.setTargetValue(heartRateTarget.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(heartRateTarget.getGarminLow());
        step.setCustomTargetValueHigh(heartRateTarget.getGarminHigh());

        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceHeartRateWorkoutStep that = (DistanceHeartRateWorkoutStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(heartRateTarget, that.heartRateTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, heartRateTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceHeartRateWorkoutStep;
    }

}
