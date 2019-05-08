package com.johnpickup.converter;

import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.parser.Pace;
import com.johnpickup.parser.PaceName;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Convert an independent pace range into a Garmin pace target
 */
@RequiredArgsConstructor
public class PaceNameConverter implements PaceConverter {
    private final Map<String, PaceTarget> namedPaceTargets;
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
