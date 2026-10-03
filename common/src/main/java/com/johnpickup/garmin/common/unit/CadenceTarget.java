package com.johnpickup.garmin.common.unit;

/**
 * Cadence target - a custom minimum and maximum cadence (in subclasses)
 */
public abstract class CadenceTarget implements Target {

    @Override
    public TargetType getTargetType() {
        return TargetType.CADENCE;
    }

    public abstract Long getGarminLow();

    public abstract Long getGarminHigh();

    public abstract Long getTargetValue();
}
