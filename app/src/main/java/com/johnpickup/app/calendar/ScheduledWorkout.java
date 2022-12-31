package com.johnpickup.app.calendar;

import java.time.LocalDate;
import java.util.Objects;

public class ScheduledWorkout {
    private final String name;
    private final String description;
    private final LocalDate date;

    public ScheduledWorkout(String name, String description, LocalDate date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledWorkout that = (ScheduledWorkout) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ScheduledWorkout;
    }

    public String toString() {
        return "ScheduledWorkout(name=" + this.getName() + ", description=" + this.getDescription() + ", date=" + this.getDate() + ")";
    }
}
