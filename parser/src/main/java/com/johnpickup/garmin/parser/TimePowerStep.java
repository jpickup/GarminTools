package com.johnpickup.garmin.parser;

import java.util.Objects;

public class TimePowerStep extends Step {
    private final Time time;
    private final Power power;

    public TimePowerStep(Time time, Power power) {
        super();
        this.time = time;
        this.power = power;
    }

    public TimePowerStep(StepIntensity stepIntensity, Time time, Power power) {
        super(stepIntensity);
        this.time = time;
        this.power = power;
    }

    @Override
    public String toString() {
        return time + "@" + power + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePowerStep that = (TimePowerStep) o;
        return Objects.equals(time, that.time) && Objects.equals(power, that.power)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, power);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimePowerStep;
    }

    public Time getTime() {
        return this.time;
    }

    public Power getPower() {
        return this.power;
    }
}
