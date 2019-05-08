package com.johnpickup.garmin.converter;

import com.johnpickup.garmin.route.Course;
import com.johnpickup.garmin.route.CoursePoint;
import com.johnpickup.gpx.GpxType;
import com.johnpickup.gpx.RteType;
import com.johnpickup.gpx.WptType;

import java.util.ArrayList;
import java.util.List;

public class CourseConverter {
    public Course convert(GpxType gpx) {
        RteType rteType = gpx.getRte().get(0);
        String name = rteType.getName();
        List<CoursePoint> points = new ArrayList<>();
        for (WptType wptType : rteType.getRtept()) {
            points.add(wayPointToCoursePoint(wptType));
        }
        return new Course(name, points);
    }

    private CoursePoint wayPointToCoursePoint(WptType wptType) {
        return new CoursePoint(
                Math.toRadians(wptType.getLat().doubleValue()),
                Math.toRadians(wptType.getLon().doubleValue()),
                wptType.getEle().doubleValue());
    }
}
