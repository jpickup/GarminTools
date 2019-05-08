package com.johnpickup.converter;

import com.johnpickup.garmin.unit.CustomHeartRateTarget;
import com.johnpickup.garmin.unit.HeartRateTarget;
import com.johnpickup.garmin.unit.HeartRateUnit;
import com.johnpickup.parser.HeartRate;
import com.johnpickup.parser.HeartRateRange;

public class CustomHeartRateConverter implements HeartRateConverter {
    @Override
    public HeartRateTarget convert(HeartRate heartRate) {
        HeartRateRange heartRateRange = (HeartRateRange) heartRate;
        HeartRateUnit unit;
        switch (heartRateRange.getUnit()) {
            case BPM:
                unit = HeartRateUnit.BEATS_PER_MINUTE;
                break;
            default:
                throw new RuntimeException("Unknown heart rate unit: " + heartRateRange.getUnit());
        }

        return new CustomHeartRateTarget(heartRateRange.getMinimum(), heartRateRange. getMaximum(), unit);
    }
}
