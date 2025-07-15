package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.common.unit.ZonePowerTarget;
import com.johnpickup.garmin.parser.Power;
import com.johnpickup.garmin.parser.PowerZone;

public class ZonePowerConverter implements PowerConverter {
    @Override
    public PowerTarget convert(Power power) {
        PowerZone powerZone = (PowerZone) power;
        return new ZonePowerTarget(powerZone.getZoneNumber());
    }
}
