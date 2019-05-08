package com.johnpickup.garmin.workout;

import com.garmin.fit.*;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Workout that repeats the component steps a specific number of times
 */
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RepeatingStepsWorkoutStep extends WorkoutStep {
    private final int intervalCount;
    private final List<WorkoutStep> steps;

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
        repeatStep.setIntensity(Intensity.INVALID);
        repeatStep.setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        repeatStep.setDurationValue((long)startIntervalIndex);
        repeatStep.setTargetType(WktStepTarget.INVALID);
        repeatStep.setTargetValue((long)intervalCount);
        repeatStep.setMessageIndex(generateWorkoutStepIndex());
        result.add(repeatStep);

        return result;
    }
}
