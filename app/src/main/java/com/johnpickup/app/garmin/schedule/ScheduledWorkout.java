package com.johnpickup.app.garmin.schedule;

import com.garmin.fit.DateTime;
import com.johnpickup.app.garmin.workout.Workout;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by john on 31/12/2016.
 */
public class ScheduledWorkout {
    private final Workout workout;
    private final LocalDate date;

    public ScheduledWorkout(Workout workout, LocalDate date) {
        this.workout = workout;
        this.date = date;
    }

    /** Get the scheduled time as a Garmin time long */
    public Long getGarminTime() {
        ZonedDateTime zdt = ZonedDateTime.of(date, LocalTime.MIDNIGHT, ZoneId.of("UTC"));
        DateTime dateTime = new DateTime(Date.from(zdt.toInstant()));
        return dateTime.getTimestamp();
    }

    public Workout getWorkout() {
        return this.workout;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "ScheduledWorkout{" +
                "workout=" + workout +
                ", date=" + date +
                '}';
    }
}
