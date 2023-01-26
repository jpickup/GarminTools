package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.HeartRateTarget;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout step that's open with a heart rate target
 */
public class OpenHeartRateWorkoutStep extends WorkoutStep {
    private final HeartRateTarget heartRateTarget;

    public OpenHeartRateWorkoutStep(HeartRateTarget heartRateTarget) {
        this.heartRateTarget = heartRateTarget;
    }

    @Override
    public String getName() {
        return "Open " + heartRateTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.OPEN);
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
        OpenHeartRateWorkoutStep that = (OpenHeartRateWorkoutStep) o;
        return Objects.equals(heartRateTarget, that.heartRateTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heartRateTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenHeartRateWorkoutStep;
    }

}
