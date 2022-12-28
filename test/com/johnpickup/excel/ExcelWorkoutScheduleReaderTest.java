package com.johnpickup.excel;

import com.johnpickup.parser.*;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by john on 10/01/2017.
 */
public class ExcelWorkoutScheduleReaderTest {
    private final static String EXCEL_TEST_FILE = "ExampleWorkoutSchedule.xls";
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

        Workout fiveMileSlow = new Workout(Collections.singletonList(new DistancePaceStep(new Distance(5, DistanceUnit.MILE), new PaceName("Slow"))));
        expected.getWorkouts().put("5mi Slow", fiveMileSlow);
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

        expected.getSchedule().add(new ScheduledWorkout(buildDate(2017,1,10), fiveMileSlow, "5mi Slow", "5mi@Slow"));
        expected.getSchedule().add(new ScheduledWorkout(buildDate(2017,1,20), intervalWorkout, "Interval", "Interval"));
        expected.getSchedule().add(new ScheduledWorkout(buildDate(2017,1,30), sixMileSteady,"6mi Steady", "6mi@Steady"));
        List<Step> onePlusFourSteps = new ArrayList<>();
        onePlusFourSteps.add(new DistanceStep(new Distance(1, DistanceUnit.MILE)));
        onePlusFourSteps.add(new DistancePaceStep(new Distance(4, DistanceUnit.MILE), new PaceName("Brisk")));
        Workout onePlusFourMileBrisk = new Workout(onePlusFourSteps);
        expected.getSchedule().add(new ScheduledWorkout(buildDate(2017,2,1), onePlusFourMileBrisk, "onePlusFourMileBrisk", "onePlusFourMileBrisk"));
        Workout threeMileExplicit = new Workout(Collections.singletonList(new DistancePaceStep(new Distance(3, DistanceUnit.MILE), new PaceRange(new Time(8,0), new Time(10,0), PaceUnit.MIN_PER_MILE))));
        expected.getSchedule().add(new ScheduledWorkout(buildDate(2017,2,11), threeMileExplicit, "threeMileExplicit", "threeMileExplicit"));


        expected.getWorkouts().put("1mi+4mi@Brisk",onePlusFourMileBrisk);
        expected.getWorkouts().put("3mi@8:00-10:00/mi",threeMileExplicit);
        return expected;
    }

    private Date buildDate(int year, int month, int day) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.clear();
        calendar.set(year, month-1, day, 0, 0, 0);

        return calendar.getTime();
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