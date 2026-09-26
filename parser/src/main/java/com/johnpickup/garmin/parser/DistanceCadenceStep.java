package com.johnpickup.garmin.parser;

import java.util.Objects;

public class DistanceCadenceStep extends Step {
    private final Distance distance;
    private final Cadence cadence;

    public DistanceCadenceStep(Distance distance, Cadence cadence) {
        super();
        this.distance = distance;
        this.cadence = cadence;
    }

    public DistanceCadenceStep(StepIntensity stepIntensity, Distance distance, Cadence cadence) {
        super(stepIntensity);
        this.distance = distance;
        this.cadence = cadence;
    }

    @Override
    public String toString() {
        return distance + "@" + cadence + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceCadenceStep that = (DistanceCadenceStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(cadence, that.cadence)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, cadence);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceCadenceStep;
    }

    public Distance getDistance() {
        return this.distance;
    }

    public Cadence getCadence() {
        return this.cadence;
    }
}
