package com.johnpickup.calendar;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class ScheduleConverter {

    public static Programme convertToProgramme(Schedule schedule) {
        Programme result = new Programme();

        LocalDate raceDate = schedule.getRaceDate();

        for (ScheduledWorkout workout : schedule.getWorkouts()) {
            String name = workout.getName();
            String description = workout.getDescription();
            long days = DAYS.between(workout.getDate(), raceDate);
            long offset = -days;
            PlannedWorkout plannedWorkout = new PlannedWorkout(name, description, offset);
            result.addWorkout(plannedWorkout);
        }

        return result;
    }

}
