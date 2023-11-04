package com.johnpickup.app.garmin.route;

import com.garmin.fit.*;
import com.johnpickup.app.garmin.fit.FitGenerator;
import com.johnpickup.app.util.Haversine;

import java.util.*;

public class Course implements FitGenerator {
    private static final double RADIAN_SEMICIRCLE = Math.pow(2, 31) / Math.PI;
    private final String name;
    private final List<CoursePoint> coursePoints;

    private Long timestamp;
    private static int instanceIndex = 0;

    public Course(String name, List<CoursePoint> coursePoints) {
        this.name = name;
        this.coursePoints = coursePoints;
    }

    public void reverse() {
        Collections.reverse(coursePoints);
    }

    @Override
    public List<Mesg> generate() {
        List<Mesg> messages = new ArrayList<>();
        messages.addAll(createMessageHeader());
        messages.addAll(createCourse());

        return messages;
    }

    private List<Mesg> createMessageHeader() {
        List<Mesg> messages = new ArrayList<>();
        FileIdMesg fileIdMesg = new FileIdMesg();
        fileIdMesg.setManufacturer( Manufacturer.GARMIN );
        fileIdMesg.setType( File.COURSE );
        fileIdMesg.setProduct(PRODUCT_ID);
        fileIdMesg.setSerialNumber(new Random().nextLong());
        fileIdMesg.setTimeCreated(new DateTime(getTimestamp()));
        messages.add(fileIdMesg);

        return messages;
    }

    private List<Mesg> createCourse() {
        List<Mesg> messages = new ArrayList<>();
        CourseMesg courseMesg = new CourseMesg();
        courseMesg.setName(name);
        courseMesg.setSport(Sport.GENERIC);
        messages.add(courseMesg);

        LapMesg lapMesg = new LapMesg();
        DateTime startTime = new DateTime(getTimestamp());
        lapMesg.setStartTime(startTime);
        CoursePoint startPoint = coursePoints.get(0);
        CoursePoint endPoint = coursePoints.get(coursePoints.size()-1);
        lapMesg.setStartPositionLat(radiansToSemicircles(startPoint.getLatitude()));
        lapMesg.setStartPositionLong(radiansToSemicircles(startPoint.getLongitude()));
        lapMesg.setEndPositionLat(radiansToSemicircles(endPoint.getLatitude()));
        lapMesg.setEndPositionLong(radiansToSemicircles(endPoint.getLongitude()));
        messages.add(lapMesg);

        EventMesg eventMesg = new EventMesg();
        eventMesg.setTimestamp(new DateTime(getTimestamp()));
        eventMesg.setEvent(Event.TIMER);
        eventMesg.setEventGroup(Short.parseShort("0"));
        eventMesg.setEventType(EventType.START);
        messages.add(eventMesg);

        float totalDistance = 0.0f;
        CoursePoint prev = startPoint;
        for (CoursePoint coursePoint : coursePoints) {
            RecordMesg recordMesg = new RecordMesg();
            recordMesg.setPositionLat(radiansToSemicircles(coursePoint.getLatitude()));
            recordMesg.setPositionLong(radiansToSemicircles(coursePoint.getLongitude()));
            recordMesg.setAltitude((float)coursePoint.getAltitude());
            recordMesg.setEnhancedAltitude((float)coursePoint.getAltitude());
            recordMesg.setTimestamp(new DateTime(getTimestamp()));
            float distance = (float)Haversine.distance(prev.getLatitude(), prev.getLongitude(), coursePoint.getLatitude(), coursePoint.getLongitude());
            totalDistance += distance;
            recordMesg.setDistance(totalDistance);
            prev = coursePoint;
            messages.add(recordMesg);
        }

        lapMesg.setTotalDistance(totalDistance);

        eventMesg = new EventMesg();
        eventMesg.setTimestamp(new DateTime(getTimestamp()));
        eventMesg.setEvent(Event.TIMER);
        eventMesg.setEventGroup(Short.parseShort("0"));
        eventMesg.setEventType(EventType.STOP_DISABLE_ALL);
        messages.add(eventMesg);

        return messages;
    }

    private static Integer radiansToSemicircles(double rad) {
        // semicircles = degrees * ( 2^31 / 180 ) = radians * 2^31 / pi
        return (int)(rad * RADIAN_SEMICIRCLE);
    }

    // Get a Garmin timestamp that is unique
    private synchronized long getTimestamp() {
        if (timestamp == null) {
            timestamp = ((new Date()).getTime() - 631065600000L) / 1000L + instanceIndex++;
        }
        return timestamp;
    }

    public int size() {
        return coursePoints.size();
    }

    public String getName() {
        return this.name;
    }
}
