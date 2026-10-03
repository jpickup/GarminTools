package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * A step that lasts a fixed distance, optionally with a target (pace, heart rate, power or cadence).
 */
public class DistanceStep extends Step {
    private final Distance distance;
    private final Target target;

    public DistanceStep(Distance distance) {
        this(null, distance, NoTarget.INSTANCE);
    }

    public DistanceStep(StepIntensity stepIntensity, Distance distance) {
        this(stepIntensity, distance, NoTarget.INSTANCE);
    }

    public DistanceStep(Distance distance, Target target) {
        this(null, distance, target);
    }

    public DistanceStep(StepIntensity stepIntensity, Distance distance, Target target) {
        super(stepIntensity);
        this.distance = distance;
        this.target = target == null ? NoTarget.INSTANCE : target;
    }

    @Override
    public String toString() {
        String targetPart = target instanceof NoTarget ? "" : ("@" + target);
        return distance + targetPart + (stepIntensity == null ? "" : ("|" + stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceStep that = (DistanceStep) o;
        return Objects.equals(distance, that.distance)
                && Objects.equals(target, that.target)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, target);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceStep;
    }

    public Distance getDistance() {
        return this.distance;
    }

    public Target getTarget() {
        return this.target;
    }
}
