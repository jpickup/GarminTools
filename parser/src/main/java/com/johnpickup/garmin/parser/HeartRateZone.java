package com.johnpickup.garmin.parser;

import java.util.Objects;

public class HeartRateZone implements HeartRate {
    private final Zone zone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeartRateZone that = (HeartRateZone) o;
        return zoneNumber == that.zoneNumber && zone == that.zone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zone, zoneNumber);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HeartRateZone;
    }

    public long getZoneNumber() {
        return this.zoneNumber;
    }

    enum Zone {
        Z1,
        Z2,
        Z3,
        Z4,
        Z5
    }
}
