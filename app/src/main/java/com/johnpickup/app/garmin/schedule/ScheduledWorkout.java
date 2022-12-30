package com.johnpickup.app.garmin.schedule;

import com.garmin.fit.DateTime;
import com.johnpickup.app.garmin.workout.Workout;

import java.util.Date;

/**
 * Created by john on 31/12/2016.
 */
public class ScheduledWorkout {
    private final Workout workout;
    private final Date date;

    public ScheduledWorkout(Workout workout, Date date) {
        this.workout = workout;
        this.date = date;
    }

    /** Get the scheduled time as a Garmin time long */
    public Long getGarminTime() {
        DateTime dateTime = new DateTime(date);
        return dateTime.getTimestamp();
    }

    public Workout getWorkout() {
        return this.workout;
    }

    public Date getDate() {
        return this.date;
    }
}
