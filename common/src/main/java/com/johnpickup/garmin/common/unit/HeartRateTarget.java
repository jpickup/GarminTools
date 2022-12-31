package com.johnpickup.garmin.common.unit;

/**
 * Heart Rate target - either a zone or a custom minimum and maximum HR (in subclasses)
 */
public abstract class HeartRateTarget{

    public abstract Long getGarminLow();

    public abstract Long getGarminHigh();

    public abstract Long getTargetValue();
}
