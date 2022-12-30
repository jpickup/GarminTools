package com.johnpickup.garmin.common.unit;

/**
 * Created by john on 22/11/2016.
 */
public enum DistanceUnit {
    METRE("metre","m"),
    KILOMETRE("kilometre","km"),
    MILE("mile","mi");

    private final String description;

    final String shortName;

    DistanceUnit(String description, String shortName) {
        this.description = description;
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getShortName() {
        return this.shortName;
    }
}
