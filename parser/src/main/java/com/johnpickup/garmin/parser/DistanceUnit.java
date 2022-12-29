package com.johnpickup.garmin.parser;

/**
 * Created by john on 03/01/2017.
 */
public enum DistanceUnit {
    METRE,
    KILOMETRE,
    MILE;

    @Override
    public String toString() {
        switch (this) {
            case METRE: return "m";
            case KILOMETRE: return "km";
            case MILE: return "mi";
            default: return super.toString();
        }
    }
}
