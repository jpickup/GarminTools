package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class TimeHeartRateStep extends Step {
    private final Time time;
    private final HeartRate heartRate;

    public TimeHeartRateStep(Time time, HeartRate heartRate) {
        super();
        this.time = time;
        this.heartRate = heartRate;
    }

    public TimeHeartRateStep(StepIntensity stepIntensity, Time time, HeartRate heartRate) {
        super(stepIntensity);
        this.time = time;
        this.heartRate = heartRate;
    }

    @Override
    public String toString() {
        return time + "@" + heartRate + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeHeartRateStep that = (TimeHeartRateStep) o;
        return Objects.equals(time, that.time) && Objects.equals(heartRate, that.heartRate)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, heartRate);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeHeartRateStep;
    }

    public Time getTime() {
        return this.time;
    }

    public HeartRate getHeartRate() {
        return this.heartRate;
    }
}
