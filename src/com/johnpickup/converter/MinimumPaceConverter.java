package com.johnpickup.converter;

import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.garmin.unit.PaceUnit;
import com.johnpickup.parser.MaximumPace;
import com.johnpickup.parser.Pace;

/**
 * Created by john on 09/01/2017.
 */
public class MinimumPaceConverter implements PaceConverter {
    @Override
    public PaceTarget convert(Pace pace) {
        MaximumPace maximumPace = (MaximumPace)pace;
        double min = TimeConverter.convert(maximumPace.getTime());
        double max = min * 2.0;
        PaceUnit unit = PaceUnitConverter.convert(maximumPace.getUnit());
        return new PaceTarget(maximumPace.toString(),min, max, unit);
    }
}
