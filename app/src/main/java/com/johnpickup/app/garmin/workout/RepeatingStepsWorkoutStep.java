package com.johnpickup.app.garmin.workout;

import com.garmin.fit.Intensity;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutStepMesg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Workout that repeats the component steps a specific number of times
 */
public class RepeatingStepsWorkoutStep extends WorkoutStep {
    private final int intervalCount;
    private final List<WorkoutStep> steps;

    public RepeatingStepsWorkoutStep(Intensity intensity, int intervalCount, List<WorkoutStep> steps) {
        super(intensity);
        this.intervalCount = intervalCount;
        this.steps = steps;
    }

    @Override
    public String getName() {
        String result = String.format("%d x (",intervalCount);
        for (WorkoutStep step : steps) {
            result += step.getName() + '+';
        }
        result = result.substring(0, result.length()-1) + ')';

        return result;
    }

    @Override
    public List<WorkoutStepMesg> generateWorkoutSteps() {
        List<WorkoutStepMesg> result = new ArrayList<>();

        for (WorkoutStep step : steps) {
            List<WorkoutStepMesg> workoutMesgs = step.generateWorkoutSteps();
            result.addAll(workoutMesgs);
        }
        int startIntervalIndex = result.get(0).getMessageIndex();

        WorkoutStepMesg repeatStep = new WorkoutStepMesg();
        repeatStep.setIntensity(Intensity.INTERVAL);
        repeatStep.setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        repeatStep.setDurationValue((long)startIntervalIndex);
        repeatStep.setTargetType(WktStepTarget.INVALID);
        repeatStep.setTargetValue((long)intervalCount);
        repeatStep.setMessageIndex(generateWorkoutStepIndex());
        repeatStep.setNotes(nameWithIntensity());
        result.add(repeatStep);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepeatingStepsWorkoutStep that = (RepeatingStepsWorkoutStep) o;
        return intervalCount == that.intervalCount && Objects.equals(steps, that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intervalCount, steps);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RepeatingStepsWorkoutStep;
    }

}
