package com.johnpickup.garmin.parser;

import java.util.Objects;

public class OpenCadenceStep extends Step {
    private final Cadence cadence;

    public OpenCadenceStep(Cadence cadence) {
        super();
        this.cadence = cadence;
    }

    public OpenCadenceStep(StepIntensity stepIntensity, Cadence cadence) {
        super(stepIntensity);
        this.cadence = cadence;
    }

    @Override
    public String toString() {
        return "Open@" + cadence + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenCadenceStep that = (OpenCadenceStep) o;
        return Objects.equals(cadence, that.cadence)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadence);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenCadenceStep;
    }

    public Cadence getCadence() {
        return this.cadence;
    }
}
