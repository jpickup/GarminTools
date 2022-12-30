package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class Distance {
    private final double quantity;
    private final DistanceUnit unit;

    public Distance(double quantity, DistanceUnit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return Double.toString(quantity) +  unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Double.compare(distance.quantity, quantity) == 0 && unit == distance.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Distance;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public DistanceUnit getUnit() {
        return this.unit;
    }
}
