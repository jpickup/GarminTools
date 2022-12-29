package com.johnpickup.garmin.parser;

import lombok.EqualsAndHashCode;

/**
 * Created by john on 03/01/2017.
 */
@EqualsAndHashCode(callSuper = true)
public class MaximumPace extends PaceLimit {
    public MaximumPace(Time time, PaceUnit unit) {
        super(time, unit);
    }

    public MaximumPace(PaceLimit paceLimit) {
        super(paceLimit.getTime(), paceLimit.getUnit());
    }

    @Override
    public String toString() {
        return String.format("<%s%s", getTime(), getUnit());
    }
}
