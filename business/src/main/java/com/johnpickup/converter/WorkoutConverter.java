package com.johnpickup.converter;

import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.Workout;
import com.johnpickup.garmin.workout.WorkoutStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert an entire independent workout into a Garmin workout with matching steps
 */
public class WorkoutConverter {
    public com.johnpickup.garmin.workout.Workout convert(Workout workout) {
        List<com.johnpickup.garmin.workout.WorkoutStep> garminWorkoutSteps = convertStepsToGarmin(workout.getSteps());
        com.johnpickup.garmin.workout.Workout result = new com.johnpickup.garmin.workout.Workout(garminWorkoutSteps);
        return result;
    }

    private List<WorkoutStep> convertStepsToGarmin(List<? extends Step> steps) {
        List<WorkoutStep> result = new ArrayList<>();
        for (Step step : steps) {
            result.add(convertStepToGarmin(step));
        }
        return result;
    }

    private WorkoutStep convertStepToGarmin(Step step) {
        return StepConverterFactory.getInstance().createFor(step).convert(step);
    }
}
