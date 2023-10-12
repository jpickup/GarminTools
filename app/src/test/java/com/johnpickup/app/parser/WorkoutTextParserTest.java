package com.johnpickup.app.parser;

import com.johnpickup.garmin.parser.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.johnpickup.garmin.parser.HeartRateZone.Z3;
import static org.junit.Assert.assertEquals;

/**
 * Created by john on 03/01/2017.
 */
public class WorkoutTextParserTest {
    private WorkoutTextParser classUnderTest;
    @Before
    public void setUp() {
        classUnderTest = new WorkoutTextParser();

    }

    @Test
    public void parseSimpleDistance() throws Exception {
        Workout actual = classUnderTest.parse("1mi");
        Workout expected = new Workout(Collections.singletonList(new DistanceStep(new Distance(1, DistanceUnit.MILE))));
        assertEquals(expected, actual);
    }

    @Test
    public void parseSimpleDistancePace() throws Exception {
        Workout actual = classUnderTest.parse("1mi@08:00-09:30/mi");
        Workout expected = new Workout(Collections.singletonList(new DistancePaceStep(new Distance(1, DistanceUnit.MILE), new PaceRange(new Time(8,0), new Time(9, 30), PaceUnit.MIN_PER_MILE))));
        assertEquals(expected, actual);
    }

    @Test
    public void parseSimpleDistanceHeartRate() throws Exception {
        Workout actual = classUnderTest.parse("1mi@100-140bpm");
        Workout expected = new Workout(Collections.singletonList(new DistanceHeartRateStep(
                new Distance(1, DistanceUnit.MILE),
                new HeartRateRange(100, 140, HeartRateUnit.BPM))));
        assertEquals(expected, actual);
    }

    @Test
    public void parseSimpleDistanceHeartRateZone() throws Exception {
        Workout actual = classUnderTest.parse("1mi@Z3");
        Workout expected = new Workout(Collections.singletonList(new DistanceHeartRateStep(
                new Distance(1, DistanceUnit.MILE), Z3)));
        assertEquals(expected, actual);
    }

    @Test
    public void parseSimpleTimeHeartRateRange() throws Exception {
        Workout actual = classUnderTest.parse("10:00@120-160bpm");
        Workout expected = new Workout(Collections.singletonList(new TimeHeartRateStep(
                new Time(10, 0),
                new HeartRateRange(120, 160, HeartRateUnit.BPM))));
        assertEquals(expected, actual);
    }

    @Test
    public void parseSimpleTimeHeartRateZone() throws Exception {
        Workout actual = classUnderTest.parse("10:00@Z3");
        Workout expected = new Workout(Collections.singletonList(new TimeHeartRateStep(
                new Time(10,0), Z3)));
        assertEquals(expected, actual);
    }
    @Test
    public void parseSimpleTime() throws Exception {
        Workout actual = classUnderTest.parse("10:00");
        Workout expected = new Workout(Collections.singletonList(new TimeStep(new Time(10,0))));
        assertEquals(expected, actual);
    }

    @Test
    public void parseSimpleTimePace() throws Exception {
        Workout actual = classUnderTest.parse("10:00@08:00-09:30/mi");
        Workout expected = new Workout(Collections.singletonList(new TimePaceStep(new Time(10,0), new PaceRange(new Time(8,0), new Time(9, 30), PaceUnit.MIN_PER_MILE))));
        assertEquals(expected, actual);
    }
    @Test
    public void parseSimpleNamedPace() throws Exception {
        Workout actual = classUnderTest.parse("1mi@FAST");
        Workout expected = new Workout(Collections.singletonList(new DistancePaceStep(new Distance(1, DistanceUnit.MILE), new PaceName("FAST"))));
        assertEquals(expected, actual);
    }

    @Test
    public void parseMultiStep() throws Exception {
        Workout actual = classUnderTest.parse("1mi + 4mi@08:00-09:30/mi + 1mi");
        List<Step> steps = new ArrayList<>();
        steps.add(new DistanceStep(new Distance(1, DistanceUnit.MILE)));
        steps.add(new DistancePaceStep(new Distance(4, DistanceUnit.MILE), new PaceRange(new Time(8,0), new Time(9, 30), PaceUnit.MIN_PER_MILE)));
        steps.add(new DistanceStep(new Distance(1, DistanceUnit.MILE)));
        Workout expected = new Workout(steps);
        assertEquals(expected, actual);
    }

    @Test
    public void parseInterval() throws Exception {
        Workout actual = classUnderTest.parse("(1mi@08:00-09:30/mi + 400m@10:00-12:30/mi) * 4");
        RepeatingSteps repeatingSteps = new RepeatingSteps(new DistancePaceStep(new Distance(1, DistanceUnit.MILE), new PaceRange(new Time(8,0), new Time(9, 30), PaceUnit.MIN_PER_MILE)));
        repeatingSteps.addStep(new DistancePaceStep(new Distance(400, DistanceUnit.METRE), new PaceRange(new Time(10,0), new Time(12, 30), PaceUnit.MIN_PER_MILE)));
        repeatingSteps.setRepetitions(4);
        Workout expected = new Workout(Collections.singletonList(repeatingSteps));
        assertEquals(expected, actual);
    }

    @Test
    public void parseRepeatingWithThree() throws Exception {
        Workout actual = classUnderTest.parse("(1mi@08:00-09:30/mi + 400m@10:00-12:30/mi + 800m@09:00-10:00/mi) * 4");
        RepeatingSteps repeatingSteps = new RepeatingSteps(new DistancePaceStep(new Distance(1, DistanceUnit.MILE), new PaceRange(new Time(8,0), new Time(9, 30), PaceUnit.MIN_PER_MILE)));
        repeatingSteps.addStep(new DistancePaceStep(new Distance(400, DistanceUnit.METRE), new PaceRange(new Time(10,0), new Time(12, 30), PaceUnit.MIN_PER_MILE)));
        repeatingSteps.addStep(new DistancePaceStep(new Distance(800, DistanceUnit.METRE), new PaceRange(new Time(9,0), new Time(10, 0), PaceUnit.MIN_PER_MILE)));
        repeatingSteps.setRepetitions(4);
        Workout expected = new Workout(Collections.singletonList(repeatingSteps));
        assertEquals(expected, actual);
    }

    @Test
    public void open() throws Exception {
        Workout actual = classUnderTest.parse("Open");
        Workout expected = new Workout(List.of(
                new OpenStep()
        ));
        assertEquals(expected, actual);
    }

    @Test
    public void parseIntensity() throws Exception {
        Workout actual = classUnderTest.parse("2mi|Warmup + 3:00|Cooldown + Open|Recovery");
        Workout expected = new Workout(Arrays.asList(
                new DistanceStep(StepIntensity.WARMUP, new Distance(2, DistanceUnit.MILE)),
                new TimeStep(StepIntensity.COOLDOWN, new Time(3, 0)),
                new OpenStep(StepIntensity.RECOVERY)
        ));
        assertEquals(expected, actual);
    }
}