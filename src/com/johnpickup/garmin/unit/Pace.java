package com.johnpickup.garmin.unit;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Encapsulation of various pace/speed standards with human-readable toString plus a conversion to Garmin units
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class Pace {
    private final double value;
    private final PaceUnit unit;

    @Override
    public String toString() {
        return String.format("%s%s", toValueString(), unit.getShortName());
    }

    public Long toGarminPace() {
        // Garmin pace units are in millimetres per second
        switch (unit) {
            case KILOMETRE_PER_HOUR: return (long)(value / 3.6 * 1000);
            case MILE_PER_HOUR: return (long)(value / 3.6 * 1609);
            case MIN_PER_MILE: return (long)(60 / value / 3.6 * 1609);
            case MIN_PER_KILOMETRE: return (long)(60 / value / 3.6 * 1000);
        }
        return null;
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
}
