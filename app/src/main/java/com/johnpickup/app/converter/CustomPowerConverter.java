package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.CustomPowerTarget;
import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.common.unit.PowerUnit;
import com.johnpickup.garmin.parser.Power;
import com.johnpickup.garmin.parser.PowerRange;

public class CustomPowerConverter implements PowerConverter {
    @Override
    public PowerTarget convert(Power power) {
        PowerRange powerRange = (PowerRange) power;
        PowerUnit unit;
        switch (powerRange.getUnit()) {
            case WATTS:
                unit = PowerUnit.WATTS;
                break;
            default:
                throw new RuntimeException("Unknown power unit: " + powerRange.getUnit());
        }

        return new CustomPowerTarget(powerRange.getMinimum(), powerRange. getMaximum(), unit);
    }
}
