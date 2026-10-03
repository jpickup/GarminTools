package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.NoTarget;
import com.johnpickup.garmin.common.unit.Target;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An open workout step (ends when the lap button is pressed), with an optional target
 * (pace, heart rate, power or cadence).
 */
public class OpenWorkoutStep extends WorkoutStep {
    private final Target target;

    public OpenWorkoutStep(Intensity intensity) {
        this(intensity, NoTarget.INSTANCE);
    }

    public OpenWorkoutStep(Intensity intensity, Target target) {
        super(intensity);
        this.target = target == null ? NoTarget.INSTANCE : target;
    }

    @Override
    public String getName() {
        String targetName = target.toString();
        return targetName.isEmpty() ? "Open" : "Open " + targetName;
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.OPEN);
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
        OpenWorkoutStep that = (OpenWorkoutStep) o;
        return Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenWorkoutStep;
    }
}
