package com.johnpickup.garmin.common.unit;

public class ZonePowerTarget extends PowerTarget {
    private final Long zone;

    public ZonePowerTarget(Long zone) {
        this.zone = zone;
    }

    @Override
    public Long getGarminLow() {
        return 0L;
    }

    @Override
    public Long getGarminHigh() {
        return 0L;
    }

    @Override
    public Long getTargetValue() {
        return zone;
    }

    @Override
    public String toString() {
        return "PZ" + zone;
    }
}
