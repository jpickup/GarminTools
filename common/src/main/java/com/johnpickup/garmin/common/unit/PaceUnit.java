package com.johnpickup.garmin.common.unit;


/**
 * Created by john on 30/12/2016.
 */
public enum PaceUnit {
    MIN_PER_MILE("min/mile","/mi"),
    MIN_PER_KILOMETRE("min/km","/km"),
    MILE_PER_HOUR("mile/hr","MPH"),
    KILOMETRE_PER_HOUR("km/hr","kph");

    private final String description;

    final String shortName;

    PaceUnit(String description, String shortName) {
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

