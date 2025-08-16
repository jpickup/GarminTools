package com.johnpickup.garmin.parser;

import java.util.Objects;

public class PowerZone implements Power {
    private final Zone zone;
    private final long zoneNumber;
    private PowerZone(Zone zone, long zoneNumber) {
        this.zone = zone;
        this.zoneNumber = zoneNumber;
    }

    public static PowerZone PZ1 = new PowerZone(Zone.PZ1, 1);
    public static PowerZone PZ2 = new PowerZone(Zone.PZ2, 2);
    public static PowerZone PZ3 = new PowerZone(Zone.PZ3, 3);
    public static PowerZone PZ4 = new PowerZone(Zone.PZ4, 4);
    public static PowerZone PZ5 = new PowerZone(Zone.PZ5, 5);
    public static PowerZone PZ6 = new PowerZone(Zone.PZ6, 6);
    public static PowerZone PZ7 = new PowerZone(Zone.PZ7, 7);

    @Override
    public String toString() {
        return zone.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerZone that = (PowerZone) o;
        return zoneNumber == that.zoneNumber && zone == that.zone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zone, zoneNumber);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PowerZone;
    }

    public long getZoneNumber() {
        return this.zoneNumber;
    }

    enum Zone {
        PZ1,
        PZ2,
        PZ3,
        PZ4,
        PZ5,
        PZ6,
        PZ7
    }
}
