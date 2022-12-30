package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.common.unit.PaceUnit;
import com.johnpickup.garmin.parser.MaximumPace;
import com.johnpickup.garmin.parser.Pace;

/**
 * Created by john on 09/01/2017.
 */
public class MaximumPaceConverter implements PaceConverter {
    @Override
    public PaceTarget convert(Pace pace) {
        MaximumPace maximumPace = (MaximumPace)pace;
        double max = TimeConverter.convert(maximumPace.getTime());
        double min = max / 2.0;
        PaceUnit unit = PaceUnitConverter.convert(maximumPace.getUnit());
        return new PaceTarget(min, max, unit);
    }
}
