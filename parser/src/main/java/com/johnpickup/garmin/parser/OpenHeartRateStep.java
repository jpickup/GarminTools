package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class OpenHeartRateStep extends Step {
    private final HeartRate heartRate;

    public OpenHeartRateStep(HeartRate heartRate) {
        super();
        this.heartRate = heartRate;
    }

    public OpenHeartRateStep(StepIntensity stepIntensity, HeartRate heartRate) {
        super(stepIntensity);
        this.heartRate = heartRate;
    }

    @Override
    public String toString() {
        return "Open@" + heartRate + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenHeartRateStep that = (OpenHeartRateStep) o;
        return Objects.equals(heartRate, that.heartRate)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heartRate);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenHeartRateStep;
    }

    public HeartRate getHeartRate() {
        return this.heartRate;
    }
}
