package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class Time {
    private final Integer minutes;
    private final Integer seconds;

    public Time(Integer minutes, Integer seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(minutes, time.minutes) && Objects.equals(seconds, time.seconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes, seconds);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Time;
    }

}
