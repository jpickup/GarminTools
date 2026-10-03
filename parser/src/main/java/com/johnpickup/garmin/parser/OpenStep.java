package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * An open step (ends when the lap button is pressed), optionally with a target
 * (pace, heart rate, power or cadence).
 */
public class OpenStep extends Step {
    private final Target target;

    public OpenStep() {
        this(null, NoTarget.INSTANCE);
    }

    public OpenStep(StepIntensity stepIntensity) {
        this(stepIntensity, NoTarget.INSTANCE);
    }

    public OpenStep(Target target) {
        this(null, target);
    }

    public OpenStep(StepIntensity stepIntensity, Target target) {
        super(stepIntensity);
        this.target = target == null ? NoTarget.INSTANCE : target;
    }

    @Override
    public String toString() {
        String targetPart = target instanceof NoTarget ? "" : ("@" + target);
        return "Open" + targetPart + (stepIntensity == null ? "" : ("|" + stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenStep that = (OpenStep) o;
        return Objects.equals(target, that.target)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenStep;
    }

    public Target getTarget() {
        return this.target;
    }
}
