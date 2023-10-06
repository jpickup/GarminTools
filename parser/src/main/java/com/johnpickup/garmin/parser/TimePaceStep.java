package com.johnpickup.garmin.parser;

import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class TimePaceStep extends Step {
    private final Time time;
    private final Pace pace;

    public TimePaceStep(Time time, Pace pace) {
        super();
        this.time = time;
        this.pace = pace;
    }

    public TimePaceStep(StepIntensity stepIntensity, Time time, Pace pace) {
        super(stepIntensity);
        this.time = time;
        this.pace = pace;
    }

    @Override
    public String toString() {
        return time + "@" + pace + (stepIntensity==null?"":("|"+stepIntensity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePaceStep that = (TimePaceStep) o;
        return Objects.equals(time, that.time) && Objects.equals(pace, that.pace)
                && Objects.equals(stepIntensity, that.stepIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, pace);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimePaceStep;
    }

    public Time getTime() {
        return this.time;
    }

    public Pace getPace() {
        return this.pace;
    }
}
