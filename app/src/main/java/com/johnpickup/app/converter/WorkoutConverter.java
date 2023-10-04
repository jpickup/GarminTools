package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.Workout;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert an entire independent workout into a Garmin workout with matching steps
 */
public class WorkoutConverter {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WorkoutConverter.class);
    public com.johnpickup.app.garmin.workout.Workout convert(Workout workout) {
        log.info("Converting parsed workout '{}' to garmin format", workout);
        List<WorkoutStep> garminWorkoutSteps = convertStepsToGarmin(workout.getSteps());
        return new com.johnpickup.app.garmin.workout.Workout(garminWorkoutSteps);
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
