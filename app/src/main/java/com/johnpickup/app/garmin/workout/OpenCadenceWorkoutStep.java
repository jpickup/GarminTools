package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.CadenceTarget;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout step that's open with a cadence target
 */
public class OpenCadenceWorkoutStep extends WorkoutStep {
    private final CadenceTarget cadenceTarget;

    public OpenCadenceWorkoutStep(Intensity intensity, CadenceTarget cadenceTarget) {
        super(intensity);
        this.cadenceTarget = cadenceTarget;
    }

    @Override
    public String getName() {
        return "Open " + cadenceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.OPEN);
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
        OpenCadenceWorkoutStep that = (OpenCadenceWorkoutStep) o;
        return Objects.equals(cadenceTarget, that.cadenceTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadenceTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenCadenceWorkoutStep;
    }
}
