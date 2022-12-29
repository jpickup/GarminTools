package com.johnpickup.app.converter;

import com.johnpickup.garmin.parser.PaceUnit;

/**
 * Convert independent pace units into Garmin pace units
 */
public class PaceUnitConverter {
    public static com.johnpickup.garmin.common.unit.PaceUnit convert(PaceUnit unit) {
        switch (unit) {
            case KILOMETRE_PER_HOUR:
                return com.johnpickup.garmin.common.unit.PaceUnit.KILOMETRE_PER_HOUR;
            case MILE_PER_HOUR:
                return com.johnpickup.garmin.common.unit.PaceUnit.MILE_PER_HOUR;
            case MIN_PER_KILOMETRE:
                return com.johnpickup.garmin.common.unit.PaceUnit.MIN_PER_KILOMETRE;
            case MIN_PER_MILE:
                return com.johnpickup.garmin.common.unit.PaceUnit.MIN_PER_MILE;
            default:
                throw new RuntimeException("Unknown pace unit " + unit);
        }
    }
}
