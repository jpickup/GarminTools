package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.common.unit.Time;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a pace target
 */
public class TimePaceWorkoutStep extends WorkoutStep {
    private final Time time;
    private final PaceTarget paceTarget;

    public TimePaceWorkoutStep(Intensity intensity, Time time, PaceTarget paceTarget) {
        super(intensity);
        this.time = time;
        this.paceTarget = paceTarget;
    }

    @Override
    public String getName() {
        return time.toString() + " " + paceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
        step.setTargetType(WktStepTarget.SPEED);
        step.setTargetValue(0L);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(paceTarget.getGarminLow());
        step.setCustomTargetValueHigh(paceTarget.getGarminHigh());
        step.setNotes(nameWithIntensity());

        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePaceWorkoutStep that = (TimePaceWorkoutStep) o;
        return Objects.equals(time, that.time) && Objects.equals(paceTarget, that.paceTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, paceTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimePaceWorkoutStep;
    }

}
