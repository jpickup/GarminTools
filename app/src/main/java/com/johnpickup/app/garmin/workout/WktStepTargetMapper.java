package com.johnpickup.app.garmin.workout;

import com.garmin.fit.WktStepTarget;
import com.johnpickup.garmin.common.unit.TargetType;

/**
 * Maps the FIT-independent {@link TargetType} used in the common module to the Garmin FIT
 * {@link WktStepTarget} value used when generating workout step messages.
 */
final class WktStepTargetMapper {
    private WktStepTargetMapper() {
    }

    static WktStepTarget toWktStepTarget(TargetType targetType) {
        return switch (targetType) {
            case NONE -> WktStepTarget.OPEN;
            case PACE -> WktStepTarget.SPEED;
            case HEART_RATE -> WktStepTarget.HEART_RATE;
            case POWER -> WktStepTarget.POWER;
            case CADENCE -> WktStepTarget.CADENCE;
        };
    }
}
