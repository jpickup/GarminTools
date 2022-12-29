package com.johnpickup.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.unit.Distance;
import com.johnpickup.garmin.unit.PaceTarget;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple workout the lasts a specific distance with a pace target
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PaceIntervalWorkoutStep extends WorkoutStep {
    private final int intervalCount;
    private final Distance intervalDistance;
    private final Distance recoveryDistance;
    private final PaceTarget intervalPaceTarget;
    private final PaceTarget recoveryPaceTarget;

    @Override
    public String getName() {
        return String.format("%dx%s %s", intervalCount, intervalDistance.toString(), intervalPaceTarget.toString());
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        List<WorkoutStepMesg> result = new ArrayList<>();

        WorkoutStepMesg intervalStep = new WorkoutStepMesg();
        intervalStep.setIntensity(Intensity.ACTIVE);
        intervalStep.setDurationType(WktStepDuration.DISTANCE);
        intervalStep.setDurationDistance(intervalDistance.toGarminDistance());
        intervalStep.setTargetType(WktStepTarget.SPEED);
        intervalStep.setTargetValue(0L);
        intervalStep.setCustomTargetValueLow(intervalPaceTarget.getGarminLow());
        intervalStep.setCustomTargetValueHigh(intervalPaceTarget.getGarminHigh());
        intervalStep.setMessageIndex(generateWorkoutStepIndex());
        result.add(intervalStep);

        WorkoutStepMesg recoveryStep = new WorkoutStepMesg();
        recoveryStep.setIntensity(Intensity.ACTIVE);
        recoveryStep.setDurationType(WktStepDuration.DISTANCE);
        recoveryStep.setDurationDistance(recoveryDistance.toGarminDistance());
        recoveryStep.setTargetType(WktStepTarget.SPEED);
        recoveryStep.setTargetValue(0L);
        recoveryStep.setCustomTargetValueLow(recoveryPaceTarget.getGarminLow());
        recoveryStep.setCustomTargetValueHigh(recoveryPaceTarget.getGarminHigh());
        recoveryStep.setMessageIndex(generateWorkoutStepIndex());
        result.add(recoveryStep);

        WorkoutStepMesg repeatStep = new WorkoutStepMesg();
        repeatStep.setIntensity(Intensity.INVALID);
        repeatStep.setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        repeatStep.setDurationValue((long)intervalStep.getMessageIndex());
        repeatStep.setTargetType(WktStepTarget.INVALID);
        repeatStep.setTargetValue((long)intervalCount);
        repeatStep.setMessageIndex(generateWorkoutStepIndex());
        result.add(repeatStep);

        return result;
    }
}
