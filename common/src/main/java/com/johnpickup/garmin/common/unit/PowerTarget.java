package com.johnpickup.garmin.common.unit;

/**
 * Power target - either a zone or a custom minimum and maximum power (in subclasses)
 */
public abstract class PowerTarget {

    public abstract Long getGarminLow();

    public abstract Long getGarminHigh();

    public abstract Long getTargetValue();
}
