package com.johnpickup.garmin.parser;

import java.util.Objects;

public class HeartRateRange implements HeartRate {
    private final int minimum;
    private final int maximum;
    private final HeartRateUnit unit;

    public HeartRateRange(int minimum, int maximum, HeartRateUnit unit) {
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
        HeartRateRange that = (HeartRateRange) o;
        return minimum == that.minimum && maximum == that.maximum && unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimum, maximum, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HeartRateRange;
    }

    public int getMinimum() {
        return this.minimum;
    }

    public int getMaximum() {
        return this.maximum;
    }

    public HeartRateUnit getUnit() {
        return this.unit;
    }
}
