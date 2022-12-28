package com.johnpickup.converter;

import com.johnpickup.parser.Time;

/**
 * Convert independent times into Garmin units
 */
public class TimeConverter {
    public static double convert(Time time) {
        return time.asDouble();
    }
}
