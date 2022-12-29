package com.johnpickup.app.calendar;

import java.time.LocalDate;

public class Scheduler {

    public ScheduledWorkout schedule(PlannedWorkout workout, LocalDate raceDate) {
        String name = workout.getName();
        String description = workout.getDescription();
        LocalDate date = raceDate.plusDays(workout.getOffset());
        return new ScheduledWorkout(name, description, date);
    }

}
