package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Power target - a minimum and maximum power in watts
 */
public class CustomPowerTarget extends PowerTarget {
    private final Power maxPower;
    private final Power minPower;

    public CustomPowerTarget(long min, long max, PowerUnit unit) {
        this.minPower = new Power(min, unit);
        this.maxPower = new Power(max, unit);
    }

    @Override
    public String toString() {
        return minPower.toValueString() + "-" + maxPower;
    }

    public Long getGarminLow() {
        if (minPower.toGarminPower() < maxPower.toGarminPower())
            return minPower.toGarminPower();
        else
            return maxPower.toGarminPower();
    }

    public Long getGarminHigh() {
        if (minPower.toGarminPower() < maxPower.toGarminPower())
            return maxPower.toGarminPower();
        else
            return minPower.toGarminPower();
    }

    @Override
    public Long getTargetValue() {
        return 0L;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomPowerTarget that = (CustomPowerTarget) o;
        return Objects.equals(maxPower, that.maxPower) && Objects.equals(minPower, that.minPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPower, minPower);
    }
}
