package com.johnpickup.app.calendar;

import java.util.ArrayList;
import java.util.Collection;

public class Programme {
    private final Collection<PlannedWorkout> workouts = new ArrayList<>();

    public void addWorkout(PlannedWorkout workout) {
        workouts.add(workout);
    }

    public Collection<PlannedWorkout> getWorkouts() {
        return this.workouts;
    }
}
