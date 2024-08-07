package com.johnpickup.app.excel;

import com.johnpickup.garmin.parser.*;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 10/01/2017.
 */
public class ExcelWorkoutScheduleReaderTest {
    private final static String EXCEL_TEST_FILE = "src/test/resources/ExampleWorkoutSchedule.xls";
    @Test
    public void read() throws Exception {
        ExcelWorkoutScheduleReader reader = new ExcelWorkoutScheduleReader();
        WorkoutSchedule actual = reader.read(new File(EXCEL_TEST_FILE));

        WorkoutSchedule expected = createExpected();

        assertEquals(expected.getPaces(), actual.getPaces());
        assertEquals(expected.getWorkouts(), actual.getWorkouts());
        assertEquals(expected.getSchedule(), actual.getSchedule());
    }

    private WorkoutSchedule createExpected() {
        WorkoutSchedule expected = new WorkoutSchedule();
        expected.getPaces().put("Fast", new PaceRange(new Time(6,0), new Time(7,0), PaceUnit.MIN_PER_MILE));
        expected.getPaces().put("Brisk", new PaceRange(new Time(7,0), new Time(8,0), PaceUnit.MIN_PER_MILE));
        expected.getPaces().put("Steady", new PaceRange(new Time(7,30), new Time(8,30), PaceUnit.MIN_PER_MILE));
        expected.getPaces().put("Easy", new PaceRange(new Time(9,0), new Time(10,30), PaceUnit.MIN_PER_MILE));
        expected.getPaces().put("Slow", new PaceRange(new Time(10,0), new Time(15,0), PaceUnit.MIN_PER_MILE));

        Workout oneMileHr = new Workout(Collections.singletonList(new DistanceHeartRateStep(
                new Distance(1, DistanceUnit.MILE),
                new HeartRateRange(140, 170, HeartRateUnit.BPM))));
        expected.getWorkouts().put("1mi@140-170bpm", oneMileHr);

        Workout fiveMileSlow = new Workout(Collections.singletonList(new DistancePaceStep(new Distance(5, DistanceUnit.MILE), new PaceName("Slow"))));
        expected.getWorkouts().put("5mi Slow", fiveMileSlow);

        Workout oneMileHrZone = new Workout(Collections.singletonList(new DistanceHeartRateStep(
                new Distance(1, DistanceUnit.MILE), HeartRateZone.Z3)));
        expected.getWorkouts().put("1mi HRZ3", oneMileHrZone);

        List<Step> intervalSteps = new ArrayList<>();
        intervalSteps.add(new DistanceStep(new Distance(1, DistanceUnit.MILE)));
        RepeatingSteps repeatingSteps = new RepeatingSteps(new DistancePaceStep(new Distance(1, DistanceUnit.MILE), new PaceName("Fast")));
        repeatingSteps.addStep(new DistancePaceStep(new Distance(400, DistanceUnit.METRE), new PaceName("Easy")));
        repeatingSteps.setRepetitions(4);
        intervalSteps.add(repeatingSteps);
        intervalSteps.add(new DistanceStep(new Distance(1, DistanceUnit.MILE)));
        Workout intervalWorkout = new Workout(intervalSteps);
        expected.getWorkouts().put("4x1mi Interval", intervalWorkout);
        Workout sixMileSteady = new Workout(Collections.singletonList(new DistancePaceStep(new Distance(6, DistanceUnit.MILE), new PaceName("Steady"))));
        expected.getWorkouts().put("6mi Steady", sixMileSteady);

        Workout halfHour = new Workout(Collections.singletonList(new TimeStep(new Time(30,0))));
        Workout fiveMinFast = new Workout(Collections.singletonList(new TimePaceStep(new Time(5,0), new PaceName("Fast"))));
        expected.getWorkouts().put("30min",halfHour);
        expected.getWorkouts().put("5min Fast", fiveMinFast);

        expected.getSchedule().add(new ScheduledWorkout(LocalDate.of(2017,1,10), fiveMileSlow, "5mi Slow", "5.0mi@Slow"));
        expected.getSchedule().add(new ScheduledWorkout(LocalDate.of(2017,1,20), intervalWorkout, "4x1mi Interval", "1.0mi + (1.0mi@Fast + 400.0m@Easy) * 4 + 1.0mi"));
        expected.getSchedule().add(new ScheduledWorkout(LocalDate.of(2017,1,30), sixMileSteady,"6mi Steady", "6.0mi@Steady"));
        List<Step> onePlusFourSteps = new ArrayList<>();
        onePlusFourSteps.add(new DistanceStep(new Distance(1, DistanceUnit.MILE)));
        onePlusFourSteps.add(new DistancePaceStep(new Distance(4, DistanceUnit.MILE), new PaceName("Brisk")));
        Workout onePlusFourMileBrisk = new Workout(onePlusFourSteps);
        expected.getSchedule().add(new ScheduledWorkout(LocalDate.of(2017,2,1), onePlusFourMileBrisk, "1mi+4mi@Brisk", "1.0mi + 4.0mi@Brisk"));
        Workout threeMileExplicit = new Workout(Collections.singletonList(new DistancePaceStep(new Distance(3, DistanceUnit.MILE), new PaceRange(new Time(8,0), new Time(10,0), PaceUnit.MIN_PER_MILE))));
        expected.getSchedule().add(new ScheduledWorkout(LocalDate.of(2017,2,11), threeMileExplicit, "3mi@8:00-10:00/mi", "3.0mi@10:00-8:00/mi"));


        expected.getWorkouts().put("1mi+4mi@Brisk",onePlusFourMileBrisk);
        expected.getWorkouts().put("3mi@8:00-10:00/mi",threeMileExplicit);
        return expected;
    }
}

/* PACE TEST DATA
Name	Value
Fast	6:00-7:00/mi
Brisk	7:00-8:00/mi
Steady	07:30-08:30/mi
Easy	09:00-10:30/mi
Slow	10:00-15:00/mi

WORKOUT TEST DATA
Name	Description
5mi Slow	5mi@Slow
4x1mi Interval	1mi + (1mi@Fast + 400m@Easy)*4 + 1mi
6mi Steady	6mi@Steady

SCHEDULE TEST DATA
10/01/2017	5mi Slow
20/01/2017	4x1mi Interval
30/01/2017	6mi Steady
01/02/2017	1mi+4mi@Brisk
11/02/2017	3mi@8:00-10:00/mi
*/