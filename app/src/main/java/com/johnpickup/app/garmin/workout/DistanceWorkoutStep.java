package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;
import com.johnpickup.garmin.common.unit.Distance;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Simple workout that lasts a specific distance, no pace targets
 */
public class DistanceWorkoutStep extends WorkoutStep {
    private final Distance distance;

    public DistanceWorkoutStep(Intensity intensity, Distance distance) {
        super(intensity);
        this.distance = distance;
    }

    @Override
    public String getName() {
        return distance.toString();
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        WorkoutStepMesg step = new WorkoutStepMesg();
        step.setIntensity(intensity);
        step.setDurationType(WktStepDuration.DISTANCE);
        step.setDurationDistance(distance.toGarminDistance());
        step.setTargetType(WktStepTarget.OPEN);
        step.setMessageIndex(generateWorkoutStepIndex());
        step.setTargetValue(0L);

        return Collections.singletonList(step);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceWorkoutStep that = (DistanceWorkoutStep) o;
        return Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DistanceWorkoutStep;
    }

}
