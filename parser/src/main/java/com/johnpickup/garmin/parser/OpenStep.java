package com.johnpickup.garmin.parser;

public class OpenStep extends Step {
    public OpenStep() {
        super();
    }

    public OpenStep(StepIntensity stepIntensity) {
        super(stepIntensity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenStep that = (OpenStep) o;
        return stepIntensity == that.stepIntensity;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OpenStep;
    }

    @Override
    public String toString() {
        return "Open" + (stepIntensity==null?"":("|"+stepIntensity));
    }

}
