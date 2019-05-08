package com.johnpickup.parser;

public enum HeartRateUnit {
    BPM;

    @Override
    public String toString() {
        switch (this) {
            case BPM: return "bpm";
            default: return super.toString();
        }
    }
}
