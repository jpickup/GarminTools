package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class TimeStep extends Step {
    private final Time time;

    public TimeStep(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return time.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeStep timeStep = (TimeStep) o;
        return Objects.equals(time, timeStep.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeStep;
    }

    public Time getTime() {
        return this.time;
    }
}
