package com.johnpickup.garmin.unit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 30/12/2016.
 */
@RequiredArgsConstructor
public enum PaceUnit {
    MIN_PER_MILE("min/mile","/mi"),
    MIN_PER_KILOMETRE("min/km","/km"),
    MILE_PER_HOUR("mile/hr","MPH"),
    KILOMETRE_PER_HOUR("km/hr","kph");

    @Getter
    private final String description;

    @Getter
    final String shortName;

    @Override
    public String toString() {
        return description;
    }
}

