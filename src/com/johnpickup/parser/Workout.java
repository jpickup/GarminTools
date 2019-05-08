package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Workout {
    private final List<? extends Step> steps;

    @Override
    public String toString() {
        String result = null;
        for (Step step : steps) {
            result = result==null?step.toString():result + " + " + step.toString();
        }
        return result;
    }
}
