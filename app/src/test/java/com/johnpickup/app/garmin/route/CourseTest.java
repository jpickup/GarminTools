package com.johnpickup.app.garmin.route;

import com.garmin.fit.Field;
import com.garmin.fit.Mesg;
import com.johnpickup.app.garmin.converter.CourseConverter;
import com.johnpickup.app.garmin.fit.FitReader;
import com.johnpickup.app.garmin.fit.FitSaver;
import com.johnpickup.app.gpx.GpxReader;
import com.johnpickup.gpx.GpxType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CourseTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CourseTest.class);
    private GpxReader gpxReader;
    private CourseConverter courseConverter;
    private String inputFilename;
    private String outputFilename;
    private String expectedFilename;
    private static final Map<Integer, Set<Integer>> ignorable = new HashMap<>();
    static {
        ignorable.put(0, new HashSet<>());
        ignorable.get(0).add(1);
        ignorable.get(0).add(2);
        ignorable.get(0).add(3);
        ignorable.get(0).add(4);
        ignorable.put(19, new HashSet<>());     // lap
        ignorable.get(19).add(2);       // lap.start time
        ignorable.get(19).add(253);     // lap.timestamp
        ignorable.get(19).add(8);       // lap.total timer time
        ignorable.put(21, new HashSet<>()); // event
        ignorable.get(21).add(253);     // event.timestamp
        ignorable.put(20, new HashSet<>());     // record
        ignorable.get(20).add(253);     // record.timestamp
    }

    @Before
    public void setUp() {
        gpxReader = new GpxReader();
        courseConverter = new CourseConverter();
        inputFilename = "src/test/resources/com.johnpickup.gpx/SimpleTest.gpx";
        outputFilename = "SimpleTest.fit";
        expectedFilename = "src/test/resources/com.johnpickup.gpx/SimpleTest.fit";
    }


    @Test
    public void generate() throws Exception {
        GpxType gpxType = gpxReader.readGpxFile(new File(inputFilename));

        Course convertedCourse = courseConverter.convert(gpxType);
        List<Mesg> actual = convertedCourse.generate();
        List<Mesg> expected = FitReader.read(new File(expectedFilename));
        assertNotNull(actual);
        assertFitEqual(expected, actual);

        FitSaver.save(convertedCourse, outputFilename);
    }

    private void assertFitEqual(List<Mesg> expected, List<Mesg> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i=0; i < expected.size(); i++) {
            log.info("Mesg #{}", i);
            Mesg expectedMesg = expected.get(i);
            Mesg actualMesg = i<actual.size()?actual.get(i):null;
            assertMesgEqual(expectedMesg, actualMesg);
        }
    }

    private void assertMesgEqual(Mesg expectedMesg, Mesg actualMesg) {
        log.info(mesgToString(expectedMesg));
        assertNotNull(actualMesg);
        assertEquals("Mesg.num", expectedMesg.getNum(), actualMesg.getNum());
        Collection<Field> fields = expectedMesg.getFields();
        for (Field expectedField : fields ) {
            if (ignorable.containsKey(expectedMesg.getNum()) && ignorable.get(expectedMesg.getNum()).contains(expectedField.getNum())) {
                continue;
            }
            Field actualField = actualMesg.getField(expectedField.getNum());
            assertFieldEqual(expectedField, actualField);
        }
    }

    private void assertFieldEqual(Field expectedField, Field actualField) {
        assertNotNull(expectedField.getName(), actualField);
        assertEquals(expectedField.getName(), expectedField.getNum(), actualField.getNum());
        assertEquals(expectedField.getName() + ".type", expectedField.getType(), actualField.getType());
        assertEquals(expectedField.getName() + ".isAccumulated", expectedField.getIsAccumulated(), actualField.getIsAccumulated());
        assertEquals(expectedField.getName() + ".value", expectedField.getRawValue(), actualField.getRawValue());
    }


    public String mesgToString(Mesg mesg) {
        StringBuilder sb = new StringBuilder();
        sb.append( "Message:\n" );

        if ( mesg.getName() != null ) {
            sb.append( "   Name: " );
            sb.append( mesg.getName() );
            sb.append('\n');
        }
        sb.append( "   Num: " );
        sb.append( mesg.getNum()  );
        sb.append('\n');
        Collection<Field> fields = mesg.getFields();
        for (Field field : fields ) {
            sb.append("Field #" + field.getNum() + " - name: " + field.getName() +
                    " (of type: " + field.getType()+") + " +
                    " profileType:" + field.getProfileType() +
                    " accumulated:" + field.getIsAccumulated() +
                    " units: " + field.getUnits() +
                    " num values " + field.getNumValues() +
                    " RAW:" + field.getRawValue() +
                    " of " + field.getRawValue().getClass().getSimpleName());
            sb.append('\n');
        }
        return sb.toString();
    }
}