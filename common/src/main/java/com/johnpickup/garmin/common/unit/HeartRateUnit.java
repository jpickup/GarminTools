package com.johnpickup.garmin.common.unit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HeartRateUnit {
    BEATS_PER_MINUTE("beats per minute","bpm");

    @Getter
    private final String description;

    @Getter
    final String shortName;

    @Override
    public String toString() {
        return description;
    }
}

