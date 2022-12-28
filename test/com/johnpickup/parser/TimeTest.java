package com.johnpickup.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test time string parsing and toString
 */
public class TimeTest {
    private static final double EPSILON = 1e-4;
    @Test
    public void parseTimeZeroSeconds() throws Exception {
        double actual = Time.parseTime("08:00").asDouble();
        double expected = 8.0;
        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void parseTimeThirtySeconds() throws Exception {
        double actual = Time.parseTime("12:30").asDouble();
        double expected = 12.5;
        assertEquals(expected, actual, EPSILON);
    }

    @Test
    public void testToString() throws Exception {
        String actual = (new Time(10,30)).toString();
        String expected = "10:30";
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringZeroMinutes() throws Exception {
        String actual = (new Time(1,0)).toString();
        String expected = "1:00";
        assertEquals(expected, actual);
    }
}