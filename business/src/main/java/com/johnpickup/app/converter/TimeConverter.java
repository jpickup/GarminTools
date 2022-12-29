package com.johnpickup.app.converter;

import com.johnpickup.garmin.parser.Time;

/**
 * Convert independent times into Garmin units
 */
public class TimeConverter {
    public static double convert(Time time) {
        return time.asDouble();
    }
}
