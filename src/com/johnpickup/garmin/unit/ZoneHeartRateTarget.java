package com.johnpickup.garmin.unit;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ZoneHeartRateTarget extends HeartRateTarget {
    private final Long zone;

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
}
