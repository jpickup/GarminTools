package com.johnpickup.garmin.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by john on 03/01/2017.
 */
public class RepeatingSteps extends Step {
    private final List<Step> steps = new ArrayList<>();
    private int repetitions = 1;

    public RepeatingSteps(Step firstStep) {
        steps.add(firstStep);
    }

    public RepeatingSteps() {
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    @Override
    public String toString() {
        StringBuilder result = null;
        for (Step step : steps) {
            if (result == null) {
                result = new StringBuilder("(" + step.toString());
            }
            else {
                result.append(" + ").append(step.toString());
            }
        }
        result.append(") * ").append(repetitions);

        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepeatingSteps that = (RepeatingSteps) o;
        return repetitions == that.repetitions && Objects.equals(steps, that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, repetitions);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RepeatingSteps;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public int getRepetitions() {
        return this.repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
