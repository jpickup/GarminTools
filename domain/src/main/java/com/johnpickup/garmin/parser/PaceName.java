package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 07/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class PaceName implements Pace {
    @Getter
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
