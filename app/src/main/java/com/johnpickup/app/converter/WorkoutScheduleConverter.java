package com.johnpickup.app.converter;

import com.johnpickup.app.garmin.schedule.TrainingSchedule;
import com.johnpickup.garmin.common.unit.PaceTarget;
import com.johnpickup.garmin.parser.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 12/01/2017.
 */
public class WorkoutScheduleConverter {
    private final Map<String, PaceTarget> namedPaces = new HashMap<>();
    private final List<com.johnpickup.app.garmin.workout.Workout> garminWorkouts = new ArrayList<>();
    private final TrainingSchedule trainingSchedule = new TrainingSchedule();
    private final Map<Workout, com.johnpickup.app.garmin.workout.Workout> workoutMap = new HashMap<>();

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
            com.johnpickup.app.garmin.workout.Workout garminWorkout = workoutConverter.convert(workoutEntry.getValue());
            garminWorkout.setName(workoutEntry.getKey());
            garminWorkouts.add(garminWorkout);
            workoutMap.put(workoutEntry.getValue(), garminWorkout);
        }

        for (ScheduledWorkout scheduledWorkout: workoutSchedule.getSchedule()) {
            Workout workout = scheduledWorkout.getWorkout();
            com.johnpickup.app.garmin.workout.Workout garminWorkout = workoutMap.get(workout);
            com.johnpickup.app.garmin.schedule.ScheduledWorkout garminScheduledWorkout =
                    new com.johnpickup.app.garmin.schedule.ScheduledWorkout(garminWorkout, scheduledWorkout.getDate());
            trainingSchedule.addScheduledWorkout(garminScheduledWorkout);
        }

    }

    private void init() {
        namedPaces.clear();
        garminWorkouts.clear();
        workoutMap.clear();
        trainingSchedule.clear();
    }

    public List<com.johnpickup.app.garmin.workout.Workout> getGarminWorkouts() {
        return this.garminWorkouts;
    }

    public TrainingSchedule getTrainingSchedule() {
        return this.trainingSchedule;
    }
}
