package com.johnpickup.garmin.common.unit;

public class ZoneHeartRateTarget extends HeartRateTarget {
    private final Long zone;

    public ZoneHeartRateTarget(Long zone) {
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
        return "Z" + zone;
    }
}
