package com.johnpickup.garmin.parser;

import java.util.*;

/**
 * Class that contains all the elements required to build a series of workouts and schedule them
 */
public class WorkoutSchedule {
    private final Map<String, Workout> workouts = new HashMap<>();
    private final Map<String, Pace> paces = new HashMap<>();
    private final List<ScheduledWorkout> schedule = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutSchedule that = (WorkoutSchedule) o;
        return workouts.equals(that.workouts) && paces.equals(that.paces) && schedule.equals(that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workouts, paces, schedule);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WorkoutSchedule;
    }

    public String toString() {
        return "WorkoutSchedule(workouts=" + this.workouts + ", paces=" + this.paces + ", schedule=" + this.schedule + ")";
    }

    public Map<String, Workout> getWorkouts() {
        return this.workouts;
    }

    public Map<String, Pace> getPaces() {
        return this.paces;
    }

    public List<ScheduledWorkout> getSchedule() {
        return this.schedule;
    }
}
