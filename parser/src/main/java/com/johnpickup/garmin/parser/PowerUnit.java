package com.johnpickup.garmin.parser;

public enum PowerUnit {
    WATTS;

    @Override
    public String toString() {
        switch (this) {
            case WATTS: return "W";
            default: return super.toString();
        }
    }
}
