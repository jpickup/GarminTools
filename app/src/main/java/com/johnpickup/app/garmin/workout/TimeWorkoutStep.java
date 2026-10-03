package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.NoTarget;
import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.common.unit.Time;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A workout step that lasts a specific time, with an optional target
 * (pace, heart rate, power or cadence).
 */
public class TimeWorkoutStep extends WorkoutStep {
    private final Time time;
    private final Target target;

    public TimeWorkoutStep(Intensity intensity, Time time) {
        this(intensity, time, NoTarget.INSTANCE);
    }

    public TimeWorkoutStep(Intensity intensity, Time time, Target target) {
        super(intensity);
        this.time = time;
        this.target = target == null ? NoTarget.INSTANCE : target;
    }

    @Override
    public String getName() {
        String targetName = target.toString();
        return targetName.isEmpty() ? time.toString() : time + " " + targetName;
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
        step.setTargetType(WktStepTargetMapper.toWktStepTarget(target.getTargetType()));
        step.setTargetValue(target.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(target.getGarminLow());
        step.setCustomTargetValueHigh(target.getGarminHigh());
        step.setNotes(nameWithIntensity());

        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeWorkoutStep that = (TimeWorkoutStep) o;
        return Objects.equals(time, that.time) && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, target);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeWorkoutStep;
    }
}
