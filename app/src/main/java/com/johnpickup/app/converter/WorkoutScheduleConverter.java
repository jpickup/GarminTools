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
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WorkoutScheduleConverter.class);
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
        log.debug("Converting paces");
        for (Map.Entry<String, Pace> namedPace : workoutSchedule.getPaces().entrySet()) {
            Pace pace = namedPace.getValue();
            log.debug("Pace: {}", pace);
            namedPaces.put(namedPace.getKey(), PaceConverterFactory.getInstance().getPaceConverter(pace).convert(pace));
        }
        log.debug("Done converting paces");

        WorkoutConverter workoutConverter = new WorkoutConverter();

        log.debug("Converting workouts");
        for (Map.Entry<String, Workout> workoutEntry : workoutSchedule.getWorkouts().entrySet()) {
            log.debug("Converting {} ", workoutEntry);
            com.johnpickup.app.garmin.workout.Workout garminWorkout = workoutConverter.convert(workoutEntry.getValue());
            garminWorkout.setName(workoutEntry.getKey());
            log.debug("Workout: {}", garminWorkout);
            garminWorkouts.add(garminWorkout);
            workoutMap.put(workoutEntry.getValue(), garminWorkout);
        }
        log.debug("Done converting workouts");

        log.debug("Converting schedules");
        for (ScheduledWorkout scheduledWorkout: workoutSchedule.getSchedule()) {
            Workout workout = scheduledWorkout.getWorkout();
            com.johnpickup.app.garmin.workout.Workout garminWorkout = workoutMap.get(workout);
            com.johnpickup.app.garmin.schedule.ScheduledWorkout garminScheduledWorkout =
                    new com.johnpickup.app.garmin.schedule.ScheduledWorkout(garminWorkout, scheduledWorkout.getDate());
            log.debug("Workout schedule: {}", garminScheduledWorkout);
            trainingSchedule.addScheduledWorkout(garminScheduledWorkout);
        }
        log.debug("Done converting schedules");

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
