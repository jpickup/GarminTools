package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DistancePaceStep extends Step {
    @Getter
    private final Distance distance;
    @Getter
    private final Pace pace;

    @Override
    public String toString() {
        return distance + "@" + pace;
    }
}
