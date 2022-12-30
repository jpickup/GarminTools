package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Heart Rate target - a minimum and maximum HR
 */
public class CustomHeartRateTarget extends HeartRateTarget {
    private final HeartRate maxHeartRate;
    private final HeartRate minHeartRate;

    public CustomHeartRateTarget(long min, long max, HeartRateUnit unit) {
        this.minHeartRate = new HeartRate(min, unit);
        this.maxHeartRate = new HeartRate(max, unit);
    }

    @Override
    public String toString() {
        return minHeartRate.toValueString() + "-" + maxHeartRate;
    }

    public Long getGarminLow() {
        if (minHeartRate.toGarminHeartRate() < maxHeartRate.toGarminHeartRate())
            return minHeartRate.toGarminHeartRate();
        else
            return maxHeartRate.toGarminHeartRate();
    }

    public Long getGarminHigh() {
        if (minHeartRate.toGarminHeartRate() < maxHeartRate.toGarminHeartRate())
            return maxHeartRate.toGarminHeartRate();
        else
            return minHeartRate.toGarminHeartRate();
    }

    @Override
    public Long getTargetValue() {
        return 0L;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomHeartRateTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomHeartRateTarget that = (CustomHeartRateTarget) o;
        return Objects.equals(maxHeartRate, that.maxHeartRate) && Objects.equals(minHeartRate, that.minHeartRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxHeartRate, minHeartRate);
    }

}
