package com.johnpickup.app.calendar;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

public class Programme {
    @Getter
    private final Collection<PlannedWorkout> workouts = new ArrayList<>();

    public void addWorkout(PlannedWorkout workout) {
        workouts.add(workout);
    }
}
