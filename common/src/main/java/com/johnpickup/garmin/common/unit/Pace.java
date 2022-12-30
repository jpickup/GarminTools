package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Encapsulation of various pace/speed standards with human-readable toString plus a conversion to Garmin units
 */
public class Pace {
    private final double value;
    private final PaceUnit unit;

    public Pace(double value, PaceUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", toValueString(), unit.getShortName());
    }

    public Long toGarminPace() {
        // Garmin pace units are in millimetres per second
        return switch (unit) {
            case KILOMETRE_PER_HOUR -> (long) (value / 3.6 * 1000);
            case MILE_PER_HOUR -> (long) (value / 3.6 * 1609);
            case MIN_PER_MILE -> (long) (60 / value / 3.6 * 1609);
            case MIN_PER_KILOMETRE -> (long) (60 / value / 3.6 * 1000);
        };
    }

    public String toValueString() {

        switch (unit) {
            case KILOMETRE_PER_HOUR:
            case MILE_PER_HOUR:
                if(value == (long) value)
                    return String.format("%d",(long)value);
                else
                    return String.format("%s", value);
            case MIN_PER_MILE:
            case MIN_PER_KILOMETRE:
                return String.format("%d:%02d", (long)value, (long)((value - (long)value)*60));
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pace pace = (Pace) o;
        return Double.compare(pace.value, value) == 0 && unit == pace.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Pace;
    }

}
