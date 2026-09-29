package com.johnpickup.garmin.common.unit;

/**
 * The Garmin-side target of a workout step. Implemented by the various target kinds
 * (pace, heart rate, power, cadence) and by {@link NoTarget} for steps with no target.
 * Exposes the values needed to populate a Garmin workout step: the target type, the target
 * value, and the custom low/high range.
 */
public interface Target {

    TargetType getTargetType();

    Long getGarminLow();

    Long getGarminHigh();

    Long getTargetValue();
}
