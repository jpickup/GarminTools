package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.PowerTarget;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout step that's open with a heart rate target
 */
public class OpenPowerWorkoutStep extends WorkoutStep {
    private final PowerTarget powerTarget;

    public OpenPowerWorkoutStep(Intensity intensity, PowerTarget powerTarget) {
        super(intensity);
        this.powerTarget = powerTarget;
    }

    @Override
    public String getName() {
        return "Open " + powerTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.OPEN);
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
        OpenPowerWorkoutStep that = (OpenPowerWorkoutStep) o;
        return Objects.equals(powerTarget, that.powerTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenPowerWorkoutStep;
    }

}
