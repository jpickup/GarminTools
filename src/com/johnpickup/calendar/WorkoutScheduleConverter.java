package com.johnpickup.calendar;

import com.johnpickup.parser.ScheduledWorkout;
import com.johnpickup.parser.WorkoutSchedule;

import java.time.LocalDate;
import java.time.ZoneId;

public class WorkoutScheduleConverter {
    public Schedule convert(WorkoutSchedule workoutSchedule) {
        Schedule result = new Schedule();

        for (ScheduledWorkout scheduledWorkout : workoutSchedule.getSchedule()) {
            com.johnpickup.calendar.ScheduledWorkout calendarScheduledWorkout = toCalendarScheduledWorkout(scheduledWorkout);
            result.add(calendarScheduledWorkout);

        }
        return result;
    }

    private com.johnpickup.calendar.ScheduledWorkout toCalendarScheduledWorkout(ScheduledWorkout scheduledWorkout) {
        String name = scheduledWorkout.getName();
        String description = scheduledWorkout.getDescription();
        LocalDate date = scheduledWorkout.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new com.johnpickup.calendar.ScheduledWorkout(name, description, date);
    }
}
