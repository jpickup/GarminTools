package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.HeartRateTarget;
import com.johnpickup.garmin.common.unit.ZoneHeartRateTarget;
import com.johnpickup.garmin.parser.HeartRate;
import com.johnpickup.garmin.parser.HeartRateZone;

public class ZoneHeartRateConverter implements HeartRateConverter {
    @Override
    public HeartRateTarget convert(HeartRate heartRate) {
        HeartRateZone heartRateZone = (HeartRateZone) heartRate;
        return new ZoneHeartRateTarget(heartRateZone.getZoneNumber());
    }
}
