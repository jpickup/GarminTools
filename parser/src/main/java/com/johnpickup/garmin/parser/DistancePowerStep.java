package com.johnpickup.garmin.parser;

import java.util.Objects;

public class DistancePowerStep extends Step {
    private final Distance distance;
    private final Power power;

    public DistancePowerStep(Distance distance, Power power) {
        super();
        this.distance = distance;
        this.power = power;
    }

    public DistancePowerStep(StepIntensity stepIntensity, Distance distance, Power power) {
        super(stepIntensity);
        this.distance = distance;
        this.power = power;
    }
    @Override
    public String toString() {
        return distance + "@" + power + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistancePowerStep that = (DistancePowerStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(power, that.power)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, power);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistancePowerStep;
    }

    public Distance getDistance() {
        return this.distance;
    }

    public Power getPower() {
        return this.power;
    }
}
