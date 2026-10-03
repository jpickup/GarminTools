package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * A step that lasts a fixed time, optionally with a target (pace, heart rate, power or cadence).
 */
public class TimeStep extends Step {
    private final Time time;
    private final Target target;

    public TimeStep(Time time) {
        this(null, time, NoTarget.INSTANCE);
    }

    public TimeStep(StepIntensity stepIntensity, Time time) {
        this(stepIntensity, time, NoTarget.INSTANCE);
    }

    public TimeStep(Time time, Target target) {
        this(null, time, target);
    }

    public TimeStep(StepIntensity stepIntensity, Time time, Target target) {
        super(stepIntensity);
        this.time = time;
        this.target = target == null ? NoTarget.INSTANCE : target;
    }

    @Override
    public String toString() {
        String targetPart = target instanceof NoTarget ? "" : ("@" + target);
        return time + targetPart + (stepIntensity == null ? "" : ("|" + stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeStep timeStep = (TimeStep) o;
        return Objects.equals(time, timeStep.time)
                && Objects.equals(target, timeStep.target)
                && Objects.equals(stepIntensity, timeStep.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, target);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeStep;
    }

    public Time getTime() {
        return this.time;
    }

    public Target getTarget() {
        return this.target;
    }
}
