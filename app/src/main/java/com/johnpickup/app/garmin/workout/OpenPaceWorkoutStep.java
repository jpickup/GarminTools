package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.PaceTarget;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout the lasts a specific distance with a pace target
 */
public class OpenPaceWorkoutStep extends WorkoutStep {
    private final PaceTarget paceTarget;

    public OpenPaceWorkoutStep(PaceTarget paceTarget) {
        this.paceTarget = paceTarget;
    }

    @Override
    public String getName() {
        return "Open " + paceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.OPEN);
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
        OpenPaceWorkoutStep that = (OpenPaceWorkoutStep) o;
        return Objects.equals(paceTarget, that.paceTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paceTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenPaceWorkoutStep;
    }

}
