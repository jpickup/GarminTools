package com.johnpickup.app.calendar;

import com.johnpickup.garmin.parser.ScheduledWorkout;
import com.johnpickup.garmin.parser.WorkoutSchedule;

import java.time.LocalDate;

public class WorkoutScheduleConverter {
    public Schedule convert(WorkoutSchedule workoutSchedule) {
        Schedule result = new Schedule();

        for (ScheduledWorkout scheduledWorkout : workoutSchedule.getSchedule()) {
            com.johnpickup.app.calendar.ScheduledWorkout calendarScheduledWorkout = toCalendarScheduledWorkout(scheduledWorkout);
            result.add(calendarScheduledWorkout);

        }
        return result;
    }

    private com.johnpickup.app.calendar.ScheduledWorkout toCalendarScheduledWorkout(ScheduledWorkout scheduledWorkout) {
        String name = scheduledWorkout.getName();
        String description = scheduledWorkout.getDescription();
        LocalDate date = scheduledWorkout.getDate();
        return new com.johnpickup.app.calendar.ScheduledWorkout(name, description, date);
    }
}
