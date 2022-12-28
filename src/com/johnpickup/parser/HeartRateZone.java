package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class HeartRateZone implements HeartRate {
    private final Zone zone;
    @Getter
    private final long zoneNumber;
    private HeartRateZone(Zone zone, long zoneNumber) {
        this.zone = zone;
        this.zoneNumber = zoneNumber;
    }

    public static HeartRateZone Z1 = new HeartRateZone(Zone.Z1, 1);
    public static HeartRateZone Z2 = new HeartRateZone(Zone.Z2, 2);
    public static HeartRateZone Z3 = new HeartRateZone(Zone.Z3, 3);
    public static HeartRateZone Z4 = new HeartRateZone(Zone.Z4, 4);
    public static HeartRateZone Z5 = new HeartRateZone(Zone.Z5, 5);

    @Override
    public String toString() {
        return zone.name();
    }

    enum Zone {
        Z1,
        Z2,
        Z3,
        Z4,
        Z5
    }
}
