package com.johnpickup.garmin.common.unit;

import java.util.Objects;

/**
 * Pace target - a minimum and maximum pace; doesn't really care about which is which (min/max pace vs min/max speed)
 * and so returns the appropriate one in the Garmin Low and High methods
 */
public class PaceTarget {
    private final String name;
    private final Pace maxPace;
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
        return Objects.requireNonNullElseGet(name, () -> minPace.toValueString() + "-" + maxPace.toString());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaceTarget that = (PaceTarget) o;
        return Objects.equals(name, that.name) && Objects.equals(maxPace, that.maxPace) && Objects.equals(minPace, that.minPace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxPace, minPace);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaceTarget;
    }

    public String getName() {
        return this.name;
    }

    public Pace getMaxPace() {
        return this.maxPace;
    }

    public Pace getMinPace() {
        return this.minPace;
    }
}
