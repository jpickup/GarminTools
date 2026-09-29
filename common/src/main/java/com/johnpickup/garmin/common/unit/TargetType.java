package com.johnpickup.garmin.common.unit;

/**
 * The kind of target a workout step has. Kept independent of the Garmin FIT SDK so that the
 * common module has no dependency on it; the app module maps these to the FIT WktStepTarget values.
 */
public enum TargetType {
    NONE,
    PACE,
    HEART_RATE,
    POWER,
    CADENCE
}
