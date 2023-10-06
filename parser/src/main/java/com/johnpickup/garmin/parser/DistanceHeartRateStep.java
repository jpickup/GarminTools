package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class DistanceHeartRateStep extends Step {
    private final Distance distance;
    private final HeartRate heartRate;

    public DistanceHeartRateStep(Distance distance, HeartRate heartRate) {
        super();
        this.distance = distance;
        this.heartRate = heartRate;
    }

    public DistanceHeartRateStep(StepIntensity stepIntensity, Distance distance, HeartRate heartRate) {
        super(stepIntensity);
        this.distance = distance;
        this.heartRate = heartRate;
    }
    @Override
    public String toString() {
        return distance + "@" + heartRate + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceHeartRateStep that = (DistanceHeartRateStep) o;
        return Objects.equals(distance, that.distance) && Objects.equals(heartRate, that.heartRate)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, heartRate);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceHeartRateStep;
    }

    public Distance getDistance() {
        return this.distance;
    }

    public HeartRate getHeartRate() {
        return this.heartRate;
    }
}
