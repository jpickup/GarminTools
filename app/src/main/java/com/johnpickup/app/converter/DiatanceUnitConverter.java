package com.johnpickup.app.converter;

import com.johnpickup.garmin.parser.DistanceUnit;

/**
 * Convert independent distance units into the Garmin type
 */
public class DiatanceUnitConverter {
    public static com.johnpickup.garmin.common.unit.DistanceUnit convert(DistanceUnit unit) {
        switch (unit) {
            case KILOMETRE:
                return com.johnpickup.garmin.common.unit.DistanceUnit.KILOMETRE;
            case METRE:
                return com.johnpickup.garmin.common.unit.DistanceUnit.METRE;
            case MILE:
                return com.johnpickup.garmin.common.unit.DistanceUnit.MILE;
            default:
                throw new RuntimeException("Unkown distance unit " + unit);
        }
    }
}
