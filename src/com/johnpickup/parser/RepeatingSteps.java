package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RepeatingSteps extends Step {
    @Getter
    private final List<Step> steps = new ArrayList<>();
    @Getter
    @Setter
    private int repetitions = 1;

    public RepeatingSteps(Step firstStep) {
        steps.add(firstStep);
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
        result.append(") * ").append(Integer.toString(repetitions));

        return result.toString();
    }
}
