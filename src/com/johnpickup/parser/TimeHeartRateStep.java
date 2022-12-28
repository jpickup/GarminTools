package com.johnpickup.parser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by john on 03/01/2017.
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TimeHeartRateStep extends Step {
    @Getter
    private final Time time;
    @Getter
    private final HeartRate heartRate;

    @Override
    public String toString() {
        return time + "@" + heartRate;
    }
}
