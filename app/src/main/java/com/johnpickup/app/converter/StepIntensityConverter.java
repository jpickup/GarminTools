package com.johnpickup.app.converter;

import com.garmin.fit.Intensity;
import com.johnpickup.garmin.parser.StepIntensity;

public class StepIntensityConverter {
    public static Intensity convert(StepIntensity stepIntensity) {
        if (stepIntensity == null) return Intensity.ACTIVE; // default if not specified
        return switch (stepIntensity) {
            case REST -> Intensity.REST;
            case ACTIVE -> Intensity.ACTIVE;
            case WARMUP -> Intensity.WARMUP;
            case COOLDOWN -> Intensity.COOLDOWN;
            case RECOVERY -> Intensity.RECOVERY;
            default -> Intensity.INVALID;
        };
    }
}
