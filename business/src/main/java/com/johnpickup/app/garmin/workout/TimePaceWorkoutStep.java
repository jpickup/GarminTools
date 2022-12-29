package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.common.unit.Time;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * Simple workout the lasts a specific distance with a pace target
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TimePaceWorkoutStep extends WorkoutStep {
    private final Time time;
    private final PaceTarget paceTarget;

    @Override
    public String getName() {
        return time.toString() + " " + paceTarget.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
        step.setTargetType(WktStepTarget.SPEED);
        step.setTargetValue(0L);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setCustomTargetValueLow(paceTarget.getGarminLow());
        step.setCustomTargetValueHigh(paceTarget.getGarminHigh());

        return Collections.singletonList(step);
    }
}
