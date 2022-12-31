package com.johnpickup.garmin.common.unit;

import java.util.Objects;

public class Distance {
    private final double value;
    private final DistanceUnit unit;

    public Distance(double value, DistanceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        if(value == (long) value)
            return String.format("%d%s",(long)value, unit.getShortName());
        else
            return String.format("%s%s", value, unit.getShortName());
    }

    public Float toGarminDistance() {
        return switch (unit) {
            case METRE -> (float) (value);
            case KILOMETRE -> (float) (value * 1000F);
            case MILE -> (float) (value * 1609F);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Double.compare(distance.value, value) == 0 && unit == distance.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Distance;
    }

}
