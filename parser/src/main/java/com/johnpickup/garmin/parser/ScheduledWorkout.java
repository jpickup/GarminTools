package com.johnpickup.garmin.parser;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by john on 11/01/2017.
 */
public class ScheduledWorkout {
    private final LocalDate date;
    private final Workout workout;
    private final String name;
    private final String description;

    public ScheduledWorkout(LocalDate date, Workout workout, String name, String description) {
        this.date = date;
        this.workout = workout;
        this.name = name;
        this.description = description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Workout getWorkout() {
        return this.workout;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduledWorkout that = (ScheduledWorkout) o;
        return Objects.equals(date, that.date) && Objects.equals(workout, that.workout) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, workout, name, description);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ScheduledWorkout;
    }

    public String toString() {
        return "ScheduledWorkout(date=" + this.getDate() + ", workout=" + this.getWorkout() + ", name=" + this.getName() + ", description=" + this.getDescription() + ")";
    }
}
