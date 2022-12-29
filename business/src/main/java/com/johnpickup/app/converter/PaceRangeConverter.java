package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.common.unit.PaceUnit;
import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.PaceRange;

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
