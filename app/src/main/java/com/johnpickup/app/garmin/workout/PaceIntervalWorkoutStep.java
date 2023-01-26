package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Distance;
import com.johnpickup.garmin.common.unit.PaceTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance with a pace target
 */
public class PaceIntervalWorkoutStep extends WorkoutStep {
    private final int intervalCount;
    private final Distance intervalDistance;
    private final Distance recoveryDistance;
    private final PaceTarget intervalPaceTarget;
    private final PaceTarget recoveryPaceTarget;

    public PaceIntervalWorkoutStep(int intervalCount, Distance intervalDistance, Distance recoveryDistance, PaceTarget intervalPaceTarget, PaceTarget recoveryPaceTarget) {
        this.intervalCount = intervalCount;
        this.intervalDistance = intervalDistance;
        this.recoveryDistance = recoveryDistance;
        this.intervalPaceTarget = intervalPaceTarget;
        this.recoveryPaceTarget = recoveryPaceTarget;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaceIntervalWorkoutStep that = (PaceIntervalWorkoutStep) o;
        return intervalCount == that.intervalCount && Objects.equals(intervalDistance, that.intervalDistance) && Objects.equals(recoveryDistance, that.recoveryDistance) && Objects.equals(intervalPaceTarget, that.intervalPaceTarget) && Objects.equals(recoveryPaceTarget, that.recoveryPaceTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intervalCount, intervalDistance, recoveryDistance, intervalPaceTarget, recoveryPaceTarget);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaceIntervalWorkoutStep;
    }

}
