package com.johnpickup.garmin.unit;

import static org.junit.Assert.assertEquals;

public class DistanceTest {
    @org.junit.Test
    public void toStringTestKm() throws Exception {
        Distance d = new Distance(1.0, DistanceUnit.KILOMETRE);
        assertEquals("1km", d.toString());
    }

    @org.junit.Test
    public void toStringTestMile() throws Exception {
        Distance d = new Distance(1.2, DistanceUnit.MILE);
        assertEquals("1.2mi", d.toString());
    }

    @org.junit.Test
    public void toStringTestMetre() throws Exception {
        Distance d = new Distance(400, DistanceUnit.METRE);
        assertEquals("400m", d.toString());
    }
}