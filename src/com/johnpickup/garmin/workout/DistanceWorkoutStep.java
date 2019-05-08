package com.johnpickup.garmin.workout;

import com.garmin.fit.*;
import com.johnpickup.garmin.unit.Distance;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * Simple workout the lasts a specific distance, no pace targets
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DistanceWorkoutStep extends WorkoutStep {
    private final Distance distance;

    @Override
    public String getName() {
        return distance.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.OPEN);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setTargetValue(0L);

        return Collections.singletonList(step);
    }
}
