package com.johnpickup.app.garmin.workout;

import com.garmin.fit.*;
import com.johnpickup.app.garmin.fit.FitGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Workout implements FitGenerator {
    private static int instanceIndex = 0;
    private Long timestamp;
    private final List<WorkoutStep> steps;

    private String name;
    private Long serialNo;

    public Workout(List<WorkoutStep> steps) {
        this.steps = steps;
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
        return getName();
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
            serialNo = Math.abs(new Random().nextLong());
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
        workout.setSport(Sport.RUNNING);
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
