package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TimePaceStep extends Step {
    @Getter
    private final Time time;
    @Getter
    private final Pace pace;

    @Override
    public String toString() {
        return time + "@" + pace;
    }
}
