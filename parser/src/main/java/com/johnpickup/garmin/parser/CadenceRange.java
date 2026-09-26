package com.johnpickup.garmin.parser;

import java.util.Objects;

public class CadenceRange implements Cadence {
    private final int minimum;
    private final int maximum;
    private final CadenceUnit unit;

    public CadenceRange(int minimum, int maximum, CadenceUnit unit) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s-%s%s", minimum, maximum, unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadenceRange that = (CadenceRange) o;
        return minimum == that.minimum && maximum == that.maximum && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimum, maximum, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CadenceRange;
    }

    public int getMinimum() {
        return this.minimum;
    }

    public int getMaximum() {
        return this.maximum;
    }

    public CadenceUnit getUnit() {
        return this.unit;
    }
}
