package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class PaceRange implements Pace {
    @Getter
    private final Time minimum;
    @Getter
    private final Time maximum;
    @Getter
    private final PaceUnit unit;

    @Override
    public String toString() {
        return String.format("%s-%s%s",maximum, minimum, unit);
    }
}
