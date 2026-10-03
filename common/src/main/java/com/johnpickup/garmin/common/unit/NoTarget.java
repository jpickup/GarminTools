package com.johnpickup.garmin.common.unit;

/**
 * The absence of a target - used by steps that only specify a duration with no
 * pace, heart rate, power or cadence target.
 */
public class NoTarget implements Target {
    public static final NoTarget INSTANCE = new NoTarget();

    @Override
    public TargetType getTargetType() {
        return TargetType.NONE;
    }

    @Override
    public Long getGarminLow() {
        return 0L;
    }

    @Override
    public Long getGarminHigh() {
        return 0L;
    }

    @Override
    public Long getTargetValue() {
        return 0L;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return NoTarget.class.hashCode();
    }
}
