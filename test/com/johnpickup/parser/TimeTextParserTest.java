package com.johnpickup.parser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 03/01/2017.
 */
public class TimeTextParserTest {
    private TimeTextParser classUnderTest;
    @Before
    public void setUp() throws Exception {
        classUnderTest = new TimeTextParser();

    }

    @Test
    public void parseTime() throws Exception {
        Time actual = classUnderTest.parse("08:30");
        Time expected = new Time(8,30);
        assertEquals(expected, actual);
    }
}