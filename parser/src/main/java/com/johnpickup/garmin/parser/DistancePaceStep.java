package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class DistancePaceStep extends Step {
    private final Distance distance;
    private final Pace pace;

    public DistancePaceStep(Distance distance, Pace pace) {
        this.distance = distance;
        this.pace = pace;
    }

    @Override
    public String toString() {
        return distance + "@" + pace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistancePaceStep that = (DistancePaceStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(pace, that.pace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, pace);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistancePaceStep;
    }

    public Distance getDistance() {
        return this.distance;
    }

    public Pace getPace() {
        return this.pace;
    }
}
