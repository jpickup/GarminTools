package com.johnpickup.converter;

import com.johnpickup.garmin.unit.HeartRateTarget;
import com.johnpickup.garmin.unit.ZoneHeartRateTarget;
import com.johnpickup.parser.HeartRate;
import com.johnpickup.parser.HeartRateZone;

public class ZoneHeartRateConverter implements HeartRateConverter {
    @Override
    public HeartRateTarget convert(HeartRate heartRate) {
        HeartRateZone heartRateZone = (HeartRateZone) heartRate;
        return new ZoneHeartRateTarget(heartRateZone.getZoneNumber());
    }
}
