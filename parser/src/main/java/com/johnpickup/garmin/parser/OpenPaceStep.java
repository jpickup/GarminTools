package com.johnpickup.garmin.parser;

import java.util.Objects;

public class OpenPaceStep extends Step {
    private final Pace pace;

    public OpenPaceStep(Pace pace) {
        this.pace = pace;
    }

    @Override
    public String toString() {
        return "Open@" + pace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenPaceStep that = (OpenPaceStep) o;
        return Objects.equals(pace, that.pace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pace);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenPaceStep;
    }

    public Pace getPace() {
        return this.pace;
    }
}
