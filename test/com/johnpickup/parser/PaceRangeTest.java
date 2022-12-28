package com.johnpickup.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 10/01/2017.
 */
public class PaceRangeTest {
    @Test
    public void testToString() throws Exception {
        String actual = new PaceRange(new Time(10,30), new Time(8,0), PaceUnit.MIN_PER_MILE).toString();
        String expected = "8:00-10:30/mi";
        assertEquals(expected, actual);
    }

}