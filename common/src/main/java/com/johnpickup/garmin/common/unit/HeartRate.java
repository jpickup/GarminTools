package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Encapsulation of custom heart rate value with human-readable toString plus a conversion to Garmin units
 */
public class HeartRate {
    private final long value;
    private final HeartRateUnit unit;

    public HeartRate(long value, HeartRateUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", toValueString(), unit.getShortName());
    }

    public Long toGarminHeartRate() {
        // Garmin HR units are in bpm + 100 ( 0 .. 100 reserved for HR %)
        return switch (unit) {
            case BEATS_PER_MINUTE -> value + 100;
        };
    }

    public String toValueString() {
        return switch (unit) {
            case BEATS_PER_MINUTE -> String.format("%d", value);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeartRate heartRate = (HeartRate) o;
        return value == heartRate.value && unit == heartRate.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HeartRate;
    }

}
