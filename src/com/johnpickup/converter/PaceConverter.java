package com.johnpickup.converter;

import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.parser.Pace;

/**
 * Interface that pace converters must implement.
 * One converter will be implemented for each sub-type of Pace and will emit a corresponding
 * instance of a Garmin PaceTarget
 */
public interface PaceConverter {
    PaceTarget convert(Pace pace);
}
