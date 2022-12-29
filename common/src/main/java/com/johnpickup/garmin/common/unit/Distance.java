package com.johnpickup.garmin.common.unit;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Distance {
    private final double value;
    private final DistanceUnit unit;

    @Override
    public String toString() {
        if(value == (long) value)
            return String.format("%d%s",(long)value, unit.getShortName());
        else
            return String.format("%s%s", value, unit.getShortName());
    }

    public Float toGarminDistance() {
        switch (unit) {
            case METRE: return (float)(value);
            case KILOMETRE: return (float)(value * 1000F);
            case MILE: return (float)(value * 1609F);
        }
        return null;
    }
}
