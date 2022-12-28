package com.johnpickup.garmin.unit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 22/11/2016.
 */
@RequiredArgsConstructor
public enum DistanceUnit {
    METRE("metre","m"),
    KILOMETRE("kilometre","km"),
    MILE("mile","mi");

    @Getter
    private final String description;

    @Getter
    final String shortName;

    @Override
    public String toString() {
        return description;
    }
}
