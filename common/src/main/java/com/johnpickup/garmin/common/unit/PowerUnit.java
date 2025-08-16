package com.johnpickup.garmin.common.unit;

public enum PowerUnit {
    WATTS("watts","W");

    private final String description;

    final String shortName;

    PowerUnit(String description, String shortName) {
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

