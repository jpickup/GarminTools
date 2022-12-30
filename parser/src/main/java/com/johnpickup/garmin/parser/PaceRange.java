package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class PaceRange implements Pace {
    private final Time minimum;
    private final Time maximum;
    private final PaceUnit unit;

    public PaceRange(Time minimum, Time maximum, PaceUnit unit) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s-%s%s",maximum, minimum, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaceRange paceRange = (PaceRange) o;
        return Objects.equals(minimum, paceRange.minimum) && Objects.equals(maximum, paceRange.maximum) && unit == paceRange.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimum, maximum, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaceRange;
    }

    public Time getMinimum() {
        return this.minimum;
    }

    public Time getMaximum() {
        return this.maximum;
    }

    public PaceUnit getUnit() {
        return this.unit;
    }
}
