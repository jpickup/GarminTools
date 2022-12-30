package com.johnpickup.app.calendar;

import java.util.Objects;

public class PlannedWorkout {
    private final String name;
    private final String description;
    private final long offset;

    public PlannedWorkout(String name, String description, long offset) {
        this.name = name;
        this.description = description;
        this.offset = offset;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getOffset() {
        return this.offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlannedWorkout that = (PlannedWorkout) o;
        return offset == that.offset && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, offset);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PlannedWorkout;
    }

    public String toString() {
        return "PlannedWorkout(name=" + this.getName() + ", description=" + this.getDescription() + ", offset=" + this.getOffset() + ")";
    }
}
