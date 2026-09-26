package com.johnpickup.garmin.parser;

public enum CadenceUnit {
    RPM;

    @Override
    public String toString() {
        switch (this) {
            case RPM -> {
                return "rpm";
            }
            default -> {
                return super.toString();
            }
        }
    }
}
