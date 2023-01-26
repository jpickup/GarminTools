package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.PaceTarget;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a pace target
 */
public class DistancePaceWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final PaceTarget paceTarget;

    public DistancePaceWorkoutStep(Distance distance, PaceTarget paceTarget) {
        this.distance = distance;
        this.paceTarget = paceTarget;
    }

    @Override
    public String getName() {
        return distance.toString() + " " + paceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.SPEED);
        step.setTargetValue(0L);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(paceTarget.getGarminLow());
        step.setCustomTargetValueHigh(paceTarget.getGarminHigh());

        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistancePaceWorkoutStep that = (DistancePaceWorkoutStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(paceTarget, that.paceTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, paceTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistancePaceWorkoutStep;
    }

}
