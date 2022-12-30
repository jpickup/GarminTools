package com.johnpickup.garmin.parser;

/**
 * Created by john on 03/01/2017.
 */
public class MinimumPace extends PaceLimit {
    public MinimumPace(Time time, PaceUnit unit) {
        super(time, unit);
    }
    public MinimumPace(PaceLimit paceLimit) {
        super(paceLimit.getTime(), paceLimit.getUnit());
    }
    @Override
    public String toString() {
        return String.format(">%s%s", getTime(), getUnit());
    }
}
