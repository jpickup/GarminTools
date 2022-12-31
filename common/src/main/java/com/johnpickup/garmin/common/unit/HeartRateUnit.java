package com.johnpickup.garmin.common.unit;

public enum HeartRateUnit {
    BEATS_PER_MINUTE("beats per minute","bpm");

    private final String description;

    final String shortName;

    HeartRateUnit(String description, String shortName) {
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

