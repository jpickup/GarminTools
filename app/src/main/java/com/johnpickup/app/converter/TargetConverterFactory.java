package com.johnpickup.app.converter;

import com.johnpickup.garmin.common.unit.Target;
import com.johnpickup.garmin.parser.Cadence;
import com.johnpickup.garmin.parser.HeartRate;
import com.johnpickup.garmin.parser.NoTarget;
import com.johnpickup.garmin.parser.Pace;
import com.johnpickup.garmin.parser.Power;

/**
 * Converts a parsed step {@link com.johnpickup.garmin.parser.Target} into the corresponding
 * Garmin {@link Target}. Routes to the existing per-discipline converter factories (pace, heart
 * rate, power, cadence) based on the kind of target.
 */
public class TargetConverterFactory {
    private static TargetConverterFactory instance;

    private TargetConverterFactory() {
    }

    public static TargetConverterFactory getInstance() {
        if (instance == null) {
            instance = new TargetConverterFactory();
        }
        return instance;
    }

    public Target convert(com.johnpickup.garmin.parser.Target target) {
        return switch (target) {
            case null -> com.johnpickup.garmin.common.unit.NoTarget.INSTANCE;
            case NoTarget noTarget -> com.johnpickup.garmin.common.unit.NoTarget.INSTANCE;
            case Pace pace -> PaceConverterFactory.getInstance().getPaceConverter(pace).convert(pace);
            case HeartRate heartRate ->
                    HeartRateConverterFactory.getInstance().getHeartRateConverter(heartRate).convert(heartRate);
            case Power power -> PowerConverterFactory.getInstance().getPowerConverter(power).convert(power);
            case Cadence cadence -> CadenceConverterFactory.getInstance().getCadenceConverter(cadence).convert(cadence);
            default -> throw new RuntimeException("Unsupported target type: " + target.getClass().getName());
        };
    }
}
