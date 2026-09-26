package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.CadenceTarget;
import com.johnpickup.garmin.common.unit.CadenceUnit;
import com.johnpickup.garmin.common.unit.CustomCadenceTarget;
import com.johnpickup.garmin.parser.Cadence;
import com.johnpickup.garmin.parser.CadenceRange;

public class CustomCadenceConverter implements CadenceConverter {
    @Override
    public CadenceTarget convert(Cadence cadence) {
        CadenceRange cadenceRange = (CadenceRange) cadence;
        CadenceUnit unit;
        switch (cadenceRange.getUnit()) {
            case RPM -> unit = CadenceUnit.RPM;
            default -> throw new RuntimeException("Unknown cadence unit: " + cadenceRange.getUnit());
        }
        return new CustomCadenceTarget(cadenceRange.getMinimum(), cadenceRange.getMaximum(), unit);
    }
}
