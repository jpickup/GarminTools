package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Encapsulation of custom cadence value with human-readable toString plus a conversion to Garmin units
 */
public class Cadence {
    private final long value;
    private final CadenceUnit unit;

    public Cadence(long value, CadenceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", toValueString(), unit.getShortName());
    }

    public String toValueString() {
        return switch (unit) {
            case RPM -> String.format("%d", value);
        };
    }

    public Long toGarminCadence() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cadence cadence = (Cadence) o;
        return value == cadence.value && unit == cadence.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }
}
