package com.johnpickup.converter;

import com.johnpickup.garmin.parser.*;
import com.johnpickup.garmin.schedule.TrainingSchedule;
import com.johnpickup.garmin.unit.PaceTarget;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 12/01/2017.
 */
public class WorkoutScheduleConverter {
    private Map<String, PaceTarget> namedPaces = new HashMap<>();
    @Getter
    private List<com.johnpickup.garmin.workout.Workout> garminWorkouts = new ArrayList<>();
    @Getter
    private TrainingSchedule trainingSchedule = new TrainingSchedule();
    private Map<Workout, com.johnpickup.garmin.workout.Workout> workoutMap = new HashMap<>();

    public WorkoutScheduleConverter() {
        PaceNameConverter namedPaceConverter = new PaceNameConverter(namedPaces);
        PaceConverterFactory.getInstance().register(namedPaceConverter, PaceName.class);
    }

    public void convert(WorkoutSchedule workoutSchedule) {
        init();
        for (Map.Entry<String, Pace> namedPace : workoutSchedule.getPaces().entrySet()) {
            Pace pace = namedPace.getValue();
            namedPaces.put(namedPace.getKey(), PaceConverterFactory.getInstance().getPaceConverter(pace).convert(pace));
        }

        WorkoutConverter workoutConverter = new WorkoutConverter();

        for (Map.Entry<String, Workout> workoutEntry : workoutSchedule.getWorkouts().entrySet()) {
            com.johnpickup.garmin.workout.Workout garminWorkout = workoutConverter.convert(workoutEntry.getValue());
            garminWorkout.setName(workoutEntry.getKey());
            garminWorkouts.add(garminWorkout);
            workoutMap.put(workoutEntry.getValue(), garminWorkout);
        }

        for (ScheduledWorkout scheduledWorkout: workoutSchedule.getSchedule()) {
            Workout workout = scheduledWorkout.getWorkout();
            com.johnpickup.garmin.workout.Workout garminWorkout = workoutMap.get(workout);
            com.johnpickup.garmin.schedule.ScheduledWorkout garminScheduledWorkout =
                    new com.johnpickup.garmin.schedule.ScheduledWorkout(garminWorkout, scheduledWorkout.getDate());
            trainingSchedule.addScheduledWorkout(garminScheduledWorkout);
        }

    }

    private void init() {
        namedPaces.clear();
        garminWorkouts.clear();
        workoutMap.clear();
        trainingSchedule.clear();
    }
}
