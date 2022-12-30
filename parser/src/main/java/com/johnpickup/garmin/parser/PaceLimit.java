package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class PaceLimit implements Pace {
    private final Time time;
    private final PaceUnit unit;

    public PaceLimit(Time time, PaceUnit unit) {
        this.time = time;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", getTime(), getUnit());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaceLimit paceLimit = (PaceLimit) o;
        return Objects.equals(time, paceLimit.time) && unit == paceLimit.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaceLimit;
    }

    public Time getTime() {
        return this.time;
    }

    public PaceUnit getUnit() {
        return this.unit;
    }
}
