package com.johnpickup.garmin.parser;

/**
 * The absence of a target - used by steps that only specify a duration (distance, time or open)
 * with no pace, heart rate, power or cadence target.
 */
public class NoTarget implements Target {
    public static final NoTarget INSTANCE = new NoTarget();

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return NoTarget.class.hashCode();
    }
}
