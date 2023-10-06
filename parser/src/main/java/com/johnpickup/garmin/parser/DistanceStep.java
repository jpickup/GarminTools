package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class DistanceStep extends Step {
    private final Distance distance;

    public DistanceStep(Distance distance) {
        super();
        this.distance = distance;
    }

    public DistanceStep(StepIntensity stepIntensity, Distance distance) {
        super(stepIntensity);
        this.distance = distance;
    }
    @Override
    public String toString() {
        return distance.toString() + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceStep that = (DistanceStep) o;
        return Objects.equals(distance, that.distance)
                && Objects.equals(stepIntensity, that.stepIntensity);
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
