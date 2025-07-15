package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Encapsulation of custom power value with human-readable toString plus a conversion to Garmin units
 */
public class Power {
    private final long value;
    private final PowerUnit unit;

    public Power(long value, PowerUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", toValueString(), unit.getShortName());
    }

    public Long toGarminPower() {
        // Garmin power units are in watts offset by 1000
        // 0 – 1000% reserved for functional threshold power (FTP)
        return switch (unit) {
            case WATTS -> value + 1000;
        };
    }

    public String toValueString() {
        return switch (unit) {
            case WATTS -> String.format("%d", value);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Power power = (Power) o;
        return value == power.value && unit == power.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }
}
