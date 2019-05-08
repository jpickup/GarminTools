package com.johnpickup.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.unit.Distance;
import com.johnpickup.garmin.unit.HeartRateTarget;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * Simple workout the lasts a specific distance with a heart rate target
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DistanceHeartRateWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final HeartRateTarget heartRateTarget;

    @Override
    public String getName() {
        return distance.toString() + " " + heartRateTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.HEART_RATE);
        step.setTargetValue(heartRateTarget.getTargetValue());
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(heartRateTarget.getGarminLow());
        step.setCustomTargetValueHigh(heartRateTarget.getGarminHigh());

        return Collections.singletonList(step);
    }
}
