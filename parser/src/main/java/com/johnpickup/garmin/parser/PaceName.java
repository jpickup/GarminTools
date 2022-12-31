package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 07/01/2017.
 */
public class PaceName implements Pace {
    private final String name;

    public PaceName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaceName paceName = (PaceName) o;
        return Objects.equals(name, paceName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaceName;
    }

    public String getName() {
        return this.name;
    }
}
