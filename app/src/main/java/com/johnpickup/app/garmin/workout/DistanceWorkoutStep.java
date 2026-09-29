package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.NoTarget;
import com.johnpickup.garmin.common.unit.Target;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A workout step that lasts a specific distance, with an optional target
 * (pace, heart rate, power or cadence).
 */
public class DistanceWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final Target target;

    public DistanceWorkoutStep(Intensity intensity, Distance distance) {
        this(intensity, distance, NoTarget.INSTANCE);
    }

    public DistanceWorkoutStep(Intensity intensity, Distance distance, Target target) {
        super(intensity);
        this.distance = distance;
        this.target = target == null ? NoTarget.INSTANCE : target;
    }

    @Override
    public String getName() {
        String targetName = target.toString();
        return targetName.isEmpty() ? distance.toString() : distance + " " + targetName;
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
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
        DistanceWorkoutStep that = (DistanceWorkoutStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, target);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceWorkoutStep;
    }
}
