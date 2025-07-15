package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.PowerTarget;
import com.johnpickup.garmin.parser.Power;

/**
 * Interface that pace converters must implement.
 * One converter will be implemented for each sub-type of Pace and will emit a corresponding
 * instance of a Garmin PaceTarget
 */
public interface PowerConverter {
    PowerTarget convert(Power power);
}
