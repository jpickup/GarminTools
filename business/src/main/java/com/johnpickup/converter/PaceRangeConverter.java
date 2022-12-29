package com.johnpickup.converter;

import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.PaceRange;
import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.garmin.unit.PaceUnit;

/**
 * Convert an independent pace range into a Garmin pace target
 */
public class PaceRangeConverter implements PaceConverter {
    @Override
    public PaceTarget convert(Pace pace) {
        PaceRange paceRange = (PaceRange)pace;
        double min = TimeConverter.convert(paceRange.getMinimum());
        double max = TimeConverter.convert(paceRange.getMaximum());
        PaceUnit unit = PaceUnitConverter.convert(paceRange.getUnit());
        return new PaceTarget(min, max, unit);
    }
}
