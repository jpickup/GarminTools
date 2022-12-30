package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class DistanceStep extends Step {
    private final Distance distance;

    public DistanceStep(Distance distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return distance.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceStep that = (DistanceStep) o;
        return Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceStep;
    }

    public Distance getDistance() {
        return this.distance;
    }
}
