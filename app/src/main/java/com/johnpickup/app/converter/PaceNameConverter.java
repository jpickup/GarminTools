package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.PaceName;

import java.util.Map;

/**
 * Convert an independent pace range into a Garmin pace target
 */
public class PaceNameConverter implements PaceConverter {
    private final Map<String, PaceTarget> namedPaceTargets;

    public PaceNameConverter(Map<String, PaceTarget> namedPaceTargets) {
        this.namedPaceTargets = namedPaceTargets;
    }

    @Override
    public PaceTarget convert(Pace pace) {
        PaceName paceName = (PaceName)pace;
        String name = paceName.getName();
        if (namedPaceTargets.containsKey(name)) {
            return namedPaceTargets.get(name);
        }
        else {
            throw new RuntimeException("Undefined named pace " + name);
        }
    }
}
