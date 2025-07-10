package com.johnpickup.garmin.common.unit;

/**
 * Garmin time unit. value is in seconds.
 * Value in fit files is in units of 0.1s so we multiply by 10 when emitting this
 */
public class Time {
    private final double value;

    public Time(double value) {
        this.value = value;
    }

    public Float toGarminTime() {
        return (float)(value * 10);
    }

    @Override
    public String toString() {
        int seconds = (int)value % 60;
        int minutes = (int)(value / 60) % 60;
        int hours = (int)(value / 3600);
        if (hours > 0)
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        else
            return String.format("%02d:%02d", minutes, seconds);
    }
}
