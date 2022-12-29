package com.johnpickup.garmin.common.unit;

import lombok.RequiredArgsConstructor;

/**
 * Garmin time unit. value is in seconds.
 * Value in fit files is in units of 0.1s so we multiply by 10 when emitting this
 */
@RequiredArgsConstructor
public class Time {
    private final double value;
    public Float toGarminTime() {
        return (float)(value * 10);
    }
}
