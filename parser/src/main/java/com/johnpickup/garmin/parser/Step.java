package com.johnpickup.garmin.parser;

/**
 * Created by john on 03/01/2017.
 */
public abstract class Step {
    public StepIntensity getStepIntensity() {
        return stepIntensity;
    }

    public void setStepIntensity(StepIntensity stepIntensity) {
        this.stepIntensity = stepIntensity;
    }

    private StepIntensity stepIntensity;
}
