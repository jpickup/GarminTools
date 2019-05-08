package com.johnpickup.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.unit.Time;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * Simple workout the lasts a specific distance, no pace targets
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TimeWorkoutStep extends WorkoutStep {
    private final Time time;

    @Override
    public String getName() {
        return time.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(Intensity.ACTIVE);
        step.setDurationType(WktStepDuration.TIME);
        step.setDurationDistance(time.toGarminTime());
        step.setTargetType(WktStepTarget.OPEN);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setTargetValue(0L);

        return Collections.singletonList(step);
    }
}
