package com.johnpickup.garmin.parser;

import java.util.Objects;

public class OpenPowerStep extends Step {
    private final Power power;

    public OpenPowerStep(Power power) {
        super();
        this.power = power;
    }

    public OpenPowerStep(StepIntensity stepIntensity, Power power) {
        super(stepIntensity);
        this.power = power;
    }

    @Override
    public String toString() {
        return "Open@" + power + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenPowerStep that = (OpenPowerStep) o;
        return Objects.equals(power, that.power)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenPowerStep;
    }

    public Power getPower() {
        return this.power;
    }
}
