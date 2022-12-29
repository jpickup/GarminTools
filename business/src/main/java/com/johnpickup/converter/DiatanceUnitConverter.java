package com.johnpickup.converter;

import com.johnpickup.garmin.parser.DistanceUnit;

/**
 * Convert independent distance units into the Garmin type
 */
public class DiatanceUnitConverter {
    public static com.johnpickup.garmin.unit.DistanceUnit convert(DistanceUnit unit) {
        switch (unit) {
            case KILOMETRE:
                return com.johnpickup.garmin.unit.DistanceUnit.KILOMETRE;
            case METRE:
                return com.johnpickup.garmin.unit.DistanceUnit.METRE;
            case MILE:
                return com.johnpickup.garmin.unit.DistanceUnit.MILE;
            default:
                throw new RuntimeException("Unkown distance unit " + unit);
        }
    }
}
