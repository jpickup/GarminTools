package com.johnpickup.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.unit.Distance;
import com.johnpickup.garmin.unit.PaceTarget;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * Simple workout the lasts a specific distance with a pace target
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DistancePaceWorkoutStep extends WorkoutStep {
    private final Distance distance;
    private final PaceTarget paceTarget;

    @Override
    public String getName() {
        return distance.toString() + " " + paceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.SPEED);
        step.setTargetValue(0L);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(paceTarget.getGarminLow());
        step.setCustomTargetValueHigh(paceTarget.getGarminHigh());

        return Collections.singletonList(step);
    }
}
