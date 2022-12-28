package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DistanceStep extends Step {
    @Getter
    private final Distance distance;

    @Override
    public String toString() {
        return distance.toString();
    }
}
