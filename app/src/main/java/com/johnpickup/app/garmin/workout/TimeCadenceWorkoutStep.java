package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.CadenceTarget;
import com.johnpickup.garmin.common.unit.Time;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific time with a cadence target
 */
public class TimeCadenceWorkoutStep extends WorkoutStep {
    private final Time time;
    private final CadenceTarget cadenceTarget;

    public TimeCadenceWorkoutStep(Intensity intensity, Time time, CadenceTarget cadenceTarget) {
        super(intensity);
        this.time = time;
        this.cadenceTarget = cadenceTarget;
    }

    @Override
    public String getName() {
        return time.toString() + " " + cadenceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
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
        TimeCadenceWorkoutStep that = (TimeCadenceWorkoutStep) o;
        return Objects.equals(time, that.time) && Objects.equals(cadenceTarget, that.cadenceTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, cadenceTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeCadenceWorkoutStep;
    }
}
