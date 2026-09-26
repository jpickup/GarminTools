package com.johnpickup.garmin.parser;

import java.util.Objects;

public class TimeCadenceStep extends Step {
    private final Time time;
    private final Cadence cadence;

    public TimeCadenceStep(Time time, Cadence cadence) {
        super();
        this.time = time;
        this.cadence = cadence;
    }

    public TimeCadenceStep(StepIntensity stepIntensity, Time time, Cadence cadence) {
        super(stepIntensity);
        this.time = time;
        this.cadence = cadence;
    }

    @Override
    public String toString() {
        return time + "@" + cadence + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeCadenceStep that = (TimeCadenceStep) o;
        return Objects.equals(time, that.time) && Objects.equals(cadence, that.cadence)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, cadence);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeCadenceStep;
    }

    public Time getTime() {
        return this.time;
    }

    public Cadence getCadence() {
        return this.cadence;
    }
}
