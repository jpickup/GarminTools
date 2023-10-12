package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.workout.RepeatingStepsWorkoutStep;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.parser.RepeatingSteps;
import com.johnpickup.garmin.parser.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 09/01/2017.
 */
public class RepeatingStepsConverter implements StepConverter {
    @Override
    public WorkoutStep convert(Step step) {
        RepeatingSteps repeatingSteps = (RepeatingSteps)step;
        int repeatCount = repeatingSteps.getRepetitions();
        List<WorkoutStep> steps = new ArrayList<>();
        for (Step internalStep : repeatingSteps.getSteps()) {
            steps.add(StepConverterFactory.getInstance().createFor(internalStep).convert(internalStep));
        }
        RepeatingStepsWorkoutStep result =
                new RepeatingStepsWorkoutStep(StepIntensityConverter.convert(step.getStepIntensity()), repeatCount, steps);
        return result;
    }
}
