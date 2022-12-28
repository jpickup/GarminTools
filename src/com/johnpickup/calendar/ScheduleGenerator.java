package com.johnpickup.calendar;

import java.time.LocalDate;

public class ScheduleGenerator {

    private static Scheduler scheduler = new Scheduler();

    public static Schedule generate(Programme programme, LocalDate raceDate) {
        Schedule result = new Schedule();

        for (PlannedWorkout workout : programme.getWorkouts()) {
            ScheduledWorkout scheduledWorkout = scheduler.schedule(workout, raceDate);
            result.add(scheduledWorkout);
        }

        return result;
    }

}
