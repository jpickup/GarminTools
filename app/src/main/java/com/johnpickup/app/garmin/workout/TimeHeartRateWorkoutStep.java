package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.HeartRateTarget;
import com.johnpickup.garmin.common.unit.Time;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a pace target
 */
public class TimeHeartRateWorkoutStep extends WorkoutStep {
    private final Time time;
    private final HeartRateTarget heartRateTarget;

    public TimeHeartRateWorkoutStep(Intensity intensity, Time time, HeartRateTarget heartRateTarget) {
        super(intensity);
        this.time = time;
        this.heartRateTarget = heartRateTarget;
    }

    @Override
    public String getName() {
        return time.toString() + " " + heartRateTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
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
        TimeHeartRateWorkoutStep that = (TimeHeartRateWorkoutStep) o;
        return Objects.equals(time, that.time) && Objects.equals(heartRateTarget, that.heartRateTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, heartRateTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeHeartRateWorkoutStep;
    }

}
