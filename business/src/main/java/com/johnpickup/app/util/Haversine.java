package com.johnpickup.app.util;

import static java.lang.Math.*;

public class Haversine {
    private final static double DM_MEAN_RADIUS  = 6371000.0d;

    /**
     * Compute the distance between two points on the surface of the Earth
     * @param rlat1 latitude of first point in radians
     * @param rlong1 longitude of first point in radians
     * @param rlat2 latitude of second point in radians
     * @param rlong2 longitude of second point in radians
     * @return distance in metres
     */
    public static double distance(double rlat1, double rlong1, double rlat2, double rlong2)
    {
        double hs1, hs2;
        hs1 = sin((rlat1 - rlat2) / 2.0d);
        hs2 = sin((rlong1 - rlong2) / 2.0d);
        return 2.0d * DM_MEAN_RADIUS * asin(sqrt(hs1 * hs1 + cos(rlat1) * cos(rlat2) * hs2 * hs2));
    }
}
