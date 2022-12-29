package com.johnpickup.converter;

import com.johnpickup.garmin.parser.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that given a type of workout step with return an instance of the appropriate converter class
 */
public class StepConverterFactory {
    private static StepConverterFactory instance;
    private Map<Class, StepConverter> converters = new HashMap<>();

    private StepConverterFactory() {
        register(new DistanceStepConverter(), DistanceStep.class);
        register(new DistancePaceStepConverter(), DistancePaceStep.class);
        register(new DistanceHeartRateStepConverter(), DistanceHeartRateStep.class);
        register(new TimeStepConverter(), TimeStep.class);
        register(new TimePaceStepConverter(), TimePaceStep.class);
        register(new TimeHeartRateStepConverter(), TimeHeartRateStep.class);
        register(new RepeatingStepsConverter(), RepeatingSteps.class);
    }

    public static StepConverterFactory getInstance() {
        if (instance == null) {
            instance = new StepConverterFactory();
        }
        return instance;
    }

    private void register(StepConverter converter,Class stepClass) {
        converters.put(stepClass, converter);
    }

    public StepConverter createFor(Step step) {
        return converters.get(step.getClass());
    }
}
