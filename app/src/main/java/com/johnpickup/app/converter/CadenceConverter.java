package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.CadenceTarget;
import com.johnpickup.garmin.parser.Cadence;

/**
 * Interface that cadence converters must implement.
 * One converter will be implemented for each sub-type of Cadence and will emit a corresponding
 * instance of a Garmin CadenceTarget
 */
public interface CadenceConverter {
    CadenceTarget convert(Cadence cadence);
}
