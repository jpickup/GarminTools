package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@EqualsAndHashCode
@RequiredArgsConstructor
public class Time {
    private final Integer minutes;
    private final Integer seconds;

    public static Time parseTime(String text) {
        String[] parts = text.split(":");
        if (parts.length == 2) {
            return new Time(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        else {
            throw new RuntimeException("Invalid time " + text);
        }
    }

    /**
     * Return the time as a number of minutes
     * @return this time in fraction of a minute units
     */
    public Double asDouble() {
        if (minutes != null && seconds != null) {
            return (minutes + seconds/60.0);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", minutes, seconds);
    }
}
