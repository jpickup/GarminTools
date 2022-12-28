package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class HeartRateRange implements HeartRate {
    @Getter
    private final int minimum;
    @Getter
    private final int maximum;
    @Getter
    private final HeartRateUnit unit;

    @Override
    public String toString() {
        return String.format("%s-%s%s", minimum, maximum, unit);
    }
}
