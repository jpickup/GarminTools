package com.johnpickup.garmin.unit;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Pace target - a minimum and maximum pace; doesn't really care about which is which (min/max pace vs min/max speed)
 * and so returns the appropriate one in the Garmin Low and High methods
 */
@EqualsAndHashCode
public class PaceTarget {
    @Getter
    private final String name;
    @Getter
    private final Pace maxPace;
    @Getter
    private final Pace minPace;

    public PaceTarget(double min, double max, PaceUnit unit) {
        this(null, min, max, unit);
    }

    public PaceTarget(String name, double min, double max, PaceUnit unit) {
        this.name = name;
        this.minPace = new Pace(min, unit);
        this.maxPace = new Pace(max, unit);
    }

    @Override
    public String toString() {
        if (name == null) {
            return minPace.toValueString() + "-" + maxPace.toString();
        }
        else {
            return name;
        }
    }

    public Long getGarminLow() {
        if (minPace.toGarminPace() < maxPace.toGarminPace())
            return minPace.toGarminPace();
        else
            return maxPace.toGarminPace();
    }

    public Long getGarminHigh() {
        if (minPace.toGarminPace() < maxPace.toGarminPace())
            return maxPace.toGarminPace();
        else
            return minPace.toGarminPace();
    }
}
