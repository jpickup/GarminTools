package com.johnpickup.converter;

import com.johnpickup.garmin.unit.HeartRateTarget;
import com.johnpickup.parser.HeartRate;

/**
 * Interface that pace converters must implement.
 * One converter will be implemented for each sub-type of Pace and will emit a corresponding
 * instance of a Garmin PaceTarget
 */
public interface HeartRateConverter {
    HeartRateTarget convert(HeartRate heartRate);
}
