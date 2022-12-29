package com.johnpickup.converter;

import com.johnpickup.garmin.parser.HeartRate;
import com.johnpickup.garmin.parser.HeartRateZone;
import com.johnpickup.garmin.unit.HeartRateTarget;
import com.johnpickup.garmin.unit.ZoneHeartRateTarget;

public class ZoneHeartRateConverter implements HeartRateConverter {
    @Override
    public HeartRateTarget convert(HeartRate heartRate) {
        HeartRateZone heartRateZone = (HeartRateZone) heartRate;
        return new ZoneHeartRateTarget(heartRateZone.getZoneNumber());
    }
}
