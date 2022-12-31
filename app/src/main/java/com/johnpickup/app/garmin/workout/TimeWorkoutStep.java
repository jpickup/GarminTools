package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Time;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout the lasts a specific distance, no pace targets
 */
public class TimeWorkoutStep extends WorkoutStep {
    private final Time time;

    public TimeWorkoutStep(Time time) {
        this.time = time;
    }

    @Override
    public String getName() {
        return time.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
        step.setTargetType(WktStepTarget.OPEN);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setTargetValue(0L);

        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeWorkoutStep that = (TimeWorkoutStep) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeWorkoutStep;
    }

}
