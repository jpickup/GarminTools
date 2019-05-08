package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class Distance {
    @Getter
    private final double quantity;
    @Getter
    private final DistanceUnit unit;
    @Override
    public String toString() {
        return Double.toString(quantity) +  unit;
    }
}
