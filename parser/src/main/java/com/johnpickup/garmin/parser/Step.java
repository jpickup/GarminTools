package com.johnpickup.garmin.parser;

/**
 * Created by john on 03/01/2017.
 */
public abstract class Step {
    public StepIntensity getStepIntensity() {
        return stepIntensity;
    }

    public Step() {
        this.stepIntensity = null;
    }

    public Step(StepIntensity stepIntensity) {
        this.stepIntensity = stepIntensity;
    }

    protected final StepIntensity stepIntensity;
}
