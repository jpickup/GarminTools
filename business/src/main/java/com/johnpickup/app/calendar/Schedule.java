package com.johnpickup.app.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class Schedule {
    private final Collection<ScheduledWorkout> workouts = new ArrayList<>();

    public Collection<ScheduledWorkout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(ScheduledWorkout workout) {
        workouts.add(workout);
    }

    public void add(ScheduledWorkout workout) {
        workouts.add(workout);
    }

    @Override
    public String toString() {
        return "Schedule [workouts=" + workouts + "]";
    }

    public LocalDate getRaceDate() {
        LocalDate result = null;

        for (ScheduledWorkout workout : workouts) {
            if ((result == null) || (workout.getDate().compareTo(result) > 0)) {
                result = workout.getDate();
            }
        }
        return result;
    }

}
