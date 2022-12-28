package com.johnpickup.converter;

import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.garmin.unit.PaceUnit;
import com.johnpickup.parser.Pace;
import com.johnpickup.parser.PaceRange;

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
