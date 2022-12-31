package com.johnpickup.app.garmin.route;

import java.util.Objects;

public class CoursePoint {
    private final double latitude;
    private final double longitude;
    private final double altitude;

    public CoursePoint(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursePoint that = (CoursePoint) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Double.compare(that.altitude, altitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, altitude);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CoursePoint;
    }

    public String toString() {
        return "CoursePoint(latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ", altitude=" + this.getAltitude() + ")";
    }
}
