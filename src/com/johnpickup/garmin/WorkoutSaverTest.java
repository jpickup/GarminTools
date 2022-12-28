package com.johnpickup.garmin;

import com.johnpickup.converter.WorkoutConverter;
import com.johnpickup.garmin.schedule.ScheduledWorkout;
import com.johnpickup.garmin.schedule.TrainingSchedule;
import com.johnpickup.garmin.unit.Distance;
import com.johnpickup.garmin.unit.DistanceUnit;
import com.johnpickup.garmin.unit.PaceTarget;
import com.johnpickup.garmin.unit.PaceUnit;
import com.johnpickup.garmin.workout.*;
import com.johnpickup.parser.WorkoutTextParser;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 12/01/2017.
 */
public class WorkoutSaverTest {
    public static void main(String[] args) throws Exception {
        WorkoutSaver saver = new WorkoutSaver();
        WorkoutTextParser parser = new WorkoutTextParser();
        WorkoutConverter converter = new WorkoutConverter();

        com.johnpickup.parser.Workout twoMile = parser.parse("2mi");
        Workout garminTwoMileWorkout = converter.convert(twoMile);
        saver.save(garminTwoMileWorkout, "parsed_2mi.fit");

        com.johnpickup.parser.Workout interval = parser.parse("1mi + (1mi@06:00-07:00/mi + 400m) * 4 + 1mi");
        Workout garminInterval = converter.convert(interval);
        saver.save(garminInterval, "parsed_interval.fit");

        WorkoutStep testDistance = new DistanceWorkoutStep(new Distance(2, DistanceUnit.MILE));
        Workout testDistanceWorkout = new Workout(Collections.singletonList(testDistance));
        saver.save(testDistanceWorkout, "testDist.fit");

        WorkoutStep testPace = new DistancePaceWorkoutStep(new Distance(2, DistanceUnit.MILE), new PaceTarget(null, 5, 6, PaceUnit.MIN_PER_MILE));
        Workout testPaceWorkout = new Workout(Collections.singletonList(testPace));
        saver.save(testPaceWorkout, "testPace.fit");

        WorkoutStep testInterval = new PaceIntervalWorkoutStep(
                4,
                new Distance(1, DistanceUnit.MILE),
                new Distance(400, DistanceUnit.METRE),
                new PaceTarget("Brisk", 5, 7, PaceUnit.MIN_PER_MILE),
                new PaceTarget(null, 9, 12, PaceUnit.MIN_PER_MILE));
        Workout testIntervalWorkout = new Workout(Collections.singletonList(testInterval));
        testIntervalWorkout.setName("4x1mi No WU");
        saver.save(testIntervalWorkout, "testInterval1.fit");

        WorkoutStep warmcool = new DistanceWorkoutStep(new Distance(1, DistanceUnit.KILOMETRE));
        WorkoutStep testInterval2 = new PaceIntervalWorkoutStep(
                3,
                new Distance(1, DistanceUnit.KILOMETRE),
                new Distance(200, DistanceUnit.METRE),
                new PaceTarget("Fast", 4, 6, PaceUnit.MIN_PER_MILE),
                new PaceTarget(null, 9, 12, PaceUnit.MIN_PER_MILE));
        WorkoutStep[] interval2Steps = {warmcool, testInterval2, warmcool};

        Workout testIntervalWorkout2 = new Workout(Arrays.asList(interval2Steps));
        testIntervalWorkout2.setName("3x1km WUCD");
        saver.save(testIntervalWorkout2, "testInterval2.fit");

        TrainingSchedule trainingSchedule = new TrainingSchedule();
        trainingSchedule.addScheduledWorkout(new ScheduledWorkout(testDistanceWorkout, new Date()));
        trainingSchedule.addScheduledWorkout(new ScheduledWorkout(testPaceWorkout, new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1))));
        //trainingSchedule.addScheduledWorkout(new ScheduledWorkout(testInterval, new Date(2017,1,3)));
        saver.save(trainingSchedule, "testSchedule.fit");
    }
}
