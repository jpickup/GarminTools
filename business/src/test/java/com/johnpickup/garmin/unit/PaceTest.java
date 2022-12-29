package com.johnpickup.garmin.unit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 30/12/2016.
 */
public class PaceTest {
    @Test
    public void minPerKmToGarminPace() throws Exception {
        Pace pace = new Pace(5.0, PaceUnit.MIN_PER_KILOMETRE);
        Long actual = pace.toGarminPace();
        Long expected = 3333L;
        assertEquals(expected, actual);
    }

    @Test
    public void minPerMileToGarminPace() throws Exception {
        Pace pace = new Pace(8.0, PaceUnit.MIN_PER_MILE);
        Long actual = pace.toGarminPace();
        Long expected = 3352L;
        assertEquals(expected, actual);
    }

    @Test
    public void kphToGarminPace() throws Exception {
        Pace pace = new Pace(12, PaceUnit.KILOMETRE_PER_HOUR);
        Long actual = pace.toGarminPace();
        Long expected = 3333L;
        assertEquals(expected, actual);
    }

    @Test
    public void mphToGarminPace() throws Exception {
        Pace pace = new Pace(6, PaceUnit.MILE_PER_HOUR);
        Long actual = pace.toGarminPace();
        Long expected = 2681L;
        assertEquals(expected, actual);
    }

    @Test
    public void toStringTestMinPerMile() throws Exception {
        Pace pace = new Pace(6, PaceUnit.MIN_PER_MILE);
        assertEquals("6:00/mi", pace.toString());
    }

    @Test
    public void toStringTestMinPerMile2() throws Exception {
        Pace pace = new Pace(6.5, PaceUnit.MIN_PER_MILE);
        assertEquals("6:30/mi", pace.toString());
    }

    @Test
    public void toStringTestMinPerKm() throws Exception {
        Pace pace = new Pace(4.5, PaceUnit.MIN_PER_KILOMETRE);
        assertEquals("4:30/km", pace.toString());
    }

    @Test
    public void toStringTestKph() throws Exception {
        Pace pace = new Pace(5, PaceUnit.KILOMETRE_PER_HOUR);
        assertEquals("5kph", pace.toString());
    }

    @Test
    public void toStringTestMph() throws Exception {
        Pace pace = new Pace(5.6, PaceUnit.MILE_PER_HOUR);
        assertEquals("5.6MPH", pace.toString());
    }
}