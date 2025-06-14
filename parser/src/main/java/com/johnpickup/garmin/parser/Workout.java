package com.johnpickup.garmin.parser;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by john on 03/01/2017.
 */
public class Workout {
    private final List<? extends Step> steps;
    private Sport sport;

    public Workout(List<? extends Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        String result = null;
        for (Step step : steps) {
            result = result==null?step.toString():result + " + " + step.toString();
        }
        return result;
    }

    public List<? extends Step> getSteps() {
        return this.steps;
    }

    public Sport getSport() {
        return Optional.ofNullable(sport).orElse(Sport.RUNNING);
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(steps, workout.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Workout;
    }

}
