package com.johnpickup.garmin.unit;

import lombok.EqualsAndHashCode;

/**
 * Heart Rate target - either a zone or a custom minimum and maximum HR (in subclasses)
 */
@EqualsAndHashCode
public abstract class HeartRateTarget{

    public abstract Long getGarminLow();

    public abstract Long getGarminHigh();

    public abstract Long getTargetValue();
}
