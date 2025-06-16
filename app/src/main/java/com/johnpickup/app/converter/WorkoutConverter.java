package com.johnpickup.app.converter;

import com.garmin.fit.Sport;
import com.garmin.fit.SubSport;
import com.johnpickup.app.garmin.workout.WorkoutStep;
import com.johnpickup.garmin.parser.Step;
import com.johnpickup.garmin.parser.Workout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Convert an entire independent workout into a Garmin workout with matching steps
 */
public class WorkoutConverter {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WorkoutConverter.class);
    private static final Map<com.johnpickup.garmin.parser.Sport, Sport> sportMap = new HashMap<>();
    static {
        sportMap.put(com.johnpickup.garmin.parser.Sport.RUNNING, Sport.RUNNING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.ROAD_RUNNING, Sport.RUNNING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.TRAIL_RUNNING, Sport.RUNNING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.CYCLING, Sport.CYCLING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.ROAD_CYCLING, Sport.CYCLING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.MTB, Sport.CYCLING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.SWIMMING, Sport.SWIMMING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.POOL_SWIMMING, Sport.SWIMMING);
        sportMap.put(com.johnpickup.garmin.parser.Sport.OPEN_WATER_SWIMMING, Sport.SWIMMING);
    }

    private static final Map<com.johnpickup.garmin.parser.Sport, SubSport> subSportMap = new HashMap<>();
    static {
        subSportMap.put(com.johnpickup.garmin.parser.Sport.ROAD_RUNNING, SubSport.ROAD);
        subSportMap.put(com.johnpickup.garmin.parser.Sport.TRAIL_RUNNING, SubSport.TRAIL);
        subSportMap.put(com.johnpickup.garmin.parser.Sport.ROAD_CYCLING, SubSport.ROAD);
        subSportMap.put(com.johnpickup.garmin.parser.Sport.MTB, SubSport.TRAIL);
        subSportMap.put(com.johnpickup.garmin.parser.Sport.SWIMMING, SubSport.LAP_SWIMMING);
        subSportMap.put(com.johnpickup.garmin.parser.Sport.POOL_SWIMMING, SubSport.LAP_SWIMMING);
        subSportMap.put(com.johnpickup.garmin.parser.Sport.OPEN_WATER_SWIMMING, SubSport.OPEN_WATER);
    }

    public com.johnpickup.app.garmin.workout.Workout convert(Workout workout) {
        log.info("Converting parsed workout '{}' to garmin format", workout);
        List<WorkoutStep> garminWorkoutSteps = convertStepsToGarmin(workout.getSteps());
        Sport sport = sportMap.getOrDefault(workout.getSport(), Sport.RUNNING);
        SubSport subSport = subSportMap.getOrDefault(workout.getSport(), SubSport.ALL);
        return new com.johnpickup.app.garmin.workout.Workout(sport, subSport, garminWorkoutSteps);
    }

    private List<WorkoutStep> convertStepsToGarmin(List<? extends Step> steps) {
        List<WorkoutStep> result = new ArrayList<>();
        for (Step step : steps) {
            result.add(convertStepToGarmin(step));
        }
        return result;
    }

    private WorkoutStep convertStepToGarmin(Step step) {
        return StepConverterFactory.getInstance().createFor(step).convert(step);
    }
}
