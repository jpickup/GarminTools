package com.johnpickup.garmin.schedule;

import com.garmin.fit.DateTime;
import com.johnpickup.garmin.workout.Workout;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * Created by john on 31/12/2016.
 */
@RequiredArgsConstructor
public class ScheduledWorkout {
    @Getter
    private final Workout workout;
    @Getter
    private final Date date;

    /** Get the scheduled time as a Garmin time long */
    public Long getGarminTime() {
        DateTime dateTime = new DateTime(date);
        return dateTime.getTimestamp();
    }
}
