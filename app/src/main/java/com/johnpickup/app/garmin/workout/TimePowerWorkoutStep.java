package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.common.unit.Time;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a pace target
 */
public class TimePowerWorkoutStep extends WorkoutStep {
    private final Time time;
    private final PowerTarget powerTarget;

    public TimePowerWorkoutStep(Intensity intensity, Time time, PowerTarget powerTarget) {
        super(intensity);
        this.time = time;
        this.powerTarget = powerTarget;
    }

    @Override
    public String getName() {
        return time.toString() + " " + powerTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
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
        TimePowerWorkoutStep that = (TimePowerWorkoutStep) o;
        return Objects.equals(time, that.time) && Objects.equals(powerTarget, that.powerTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, powerTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimePowerWorkoutStep;
    }

}
