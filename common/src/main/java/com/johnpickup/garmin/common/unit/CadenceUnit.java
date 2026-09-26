package com.johnpickup.garmin.common.unit;

public enum CadenceUnit {
    RPM("rpm","rpm");

    private final String description;

    final String shortName;

    CadenceUnit(String description, String shortName) {
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
