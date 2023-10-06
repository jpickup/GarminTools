package com.johnpickup.app.converter;

import com.garmin.fit.Intensity;
import com.johnpickup.garmin.parser.StepIntensity;

public class StepIntensityConverter {
    public static Intensity convert(StepIntensity stepIntensity) {
        if (stepIntensity == null) return Intensity.ACTIVE; // default if not specified
        switch (stepIntensity) {
            case REST: return Intensity.REST;
            case ACTIVE: return Intensity.ACTIVE;
            case WARMUP: return Intensity.WARMUP;
            case COOLDOWN: return Intensity.COOLDOWN;
            default: return Intensity.INVALID;
        }
    }
}
