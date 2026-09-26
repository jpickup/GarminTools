package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Cadence target - a minimum and maximum cadence in rpm
 */
public class CustomCadenceTarget extends CadenceTarget {
    private final Cadence maxCadence;
    private final Cadence minCadence;

    public CustomCadenceTarget(long min, long max, CadenceUnit unit) {
        this.minCadence = new Cadence(min, unit);
        this.maxCadence = new Cadence(max, unit);
    }

    @Override
    public String toString() {
        return minCadence.toValueString() + "-" + maxCadence;
    }

    public Long getGarminLow() {
        if (minCadence.toGarminCadence() < maxCadence.toGarminCadence())
            return minCadence.toGarminCadence();
        else
            return maxCadence.toGarminCadence();
    }

    public Long getGarminHigh() {
        if (minCadence.toGarminCadence() < maxCadence.toGarminCadence())
            return maxCadence.toGarminCadence();
        else
            return minCadence.toGarminCadence();
    }

    @Override
    public Long getTargetValue() {
        return 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomCadenceTarget that = (CustomCadenceTarget) o;
        return Objects.equals(maxCadence, that.maxCadence) && Objects.equals(minCadence, that.minCadence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxCadence, minCadence);
    }
}
