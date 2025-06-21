package com.johnpickup.app.garmin.workout;

import com.garmin.fit.*;
import com.johnpickup.app.garmin.fit.FitGenerator;

import java.util.*;

public class Workout implements FitGenerator {
    private static int instanceIndex = 0;
    private static long baseSerial = new Random().nextLong(1000000L);
    private Long timestamp;
    private final List<WorkoutStep> steps;
    private final Sport sport;
    private final SubSport subSport;
    private final Integer poolLength;

    private String name;
    private Long serialNo;

    public Workout(List<WorkoutStep> steps) {
        this(Sport.RUNNING, null, null, steps);
    }

    public Workout(Sport sport, List<WorkoutStep> steps) {
        this(sport, null, null, steps);
    }

    public Workout(Sport sport, SubSport subSport, List<WorkoutStep> steps) {
        this(sport, subSport, null, steps);
    }

    public Workout(Sport sport, SubSport subSport, Integer poolLength, List<WorkoutStep> steps) {
        this.sport = sport;
        this.subSport = subSport;
        this.steps = steps;
        this.poolLength = poolLength;
    }

    public String getName() {
        if (name != null && !name.isEmpty()) {
            return name;
        }
        String result = "";
        for (WorkoutStep step : steps) {
            if (!result.isEmpty())
                result = result + " + ";
            result = result + step.getName();
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s|%s: %s",
                Optional.ofNullable(sport).map(Enum::toString).orElse("Default"),
                Optional.ofNullable(subSport).map(Enum::toString).orElse("Default"),
                getName());
    }

    // Get a Garmin timestamp that is unique for the workout.
    public synchronized long getTimestamp() {
        if (timestamp == null) {
            timestamp = ((new Date()).getTime() - 631065600000L) / 1000L + instanceIndex++;
        }
        return timestamp;
    }

    public long getSerialNo() {
        if (serialNo == null) {
            serialNo = baseSerial++;
        }
        return serialNo;
    }

    protected List<Mesg> createMessageHeader() {
        List<Mesg> messages = new ArrayList<>();
        FileIdMesg fileIdMesg = new FileIdMesg();
        fileIdMesg.setManufacturer(Manufacturer.GARMIN);
        fileIdMesg.setType(File.WORKOUT);
        fileIdMesg.setProduct(PRODUCT_ID);
        fileIdMesg.setSerialNumber(getSerialNo());
        fileIdMesg.setTimeCreated(new DateTime(getTimestamp()));
        messages.add(fileIdMesg);

        FileCreatorMesg fileCreatorMesg = new FileCreatorMesg();
        fileCreatorMesg.setSoftwareVersion(SOFTWARE_VERSION);
        fileCreatorMesg.setHardwareVersion(HARDWARE_VERSION);
        messages.add(fileCreatorMesg);

        return messages;
    }

    @Override
    public List<Mesg> generate() {
        List<Mesg> messages = createMessageHeader();
        WorkoutMesg workout = new WorkoutMesg();
        workout.setWktName(getName());
        workout.setSport(sport);
        Optional.ofNullable(subSport).ifPresent(workout::setSubSport);
        Optional.ofNullable(poolLength).ifPresent(pl -> {
                workout.setPoolLength(pl * 1.0f);
                workout.setPoolLengthUnit(DisplayMeasure.METRIC);
            }
        );
        workout.setCapabilities(32L);
        messages.add(workout);

        WorkoutStep.startNewWorkout();
        List<WorkoutStepMesg> allWorkoutStepMesgs = new ArrayList<>();
        for (WorkoutStep step : steps) {
            List<WorkoutStepMesg> workoutStepMesgs = step.generateWorkoutSteps();
            allWorkoutStepMesgs.addAll(workoutStepMesgs);
        }
        messages.addAll(allWorkoutStepMesgs);
        workout.setNumValidSteps(allWorkoutStepMesgs.size());
        return messages;
    }

    public void setName(String name) {
        this.name = name;
    }

}
