package com.johnpickup.app.garmin.converter;

import com.johnpickup.app.garmin.route.Course;
import com.johnpickup.app.garmin.route.CoursePoint;
import com.johnpickup.gpx.*;

import java.util.ArrayList;
import java.util.List;

public class CourseConverter {
    public Course convert(GpxType gpx) {
        String alternativeName = gpx.getMetadata().getName();

        if (gpx.getTrk().size() > 0) {
            return convertTrack(gpx.getTrk().get(0), alternativeName);
        }

        if (gpx.getRte().size() > 0) {
            return convertRoute(gpx.getRte().get(0), alternativeName);
        }

        throw new RuntimeException("GPX doesn't contain a route or a track, nothing to convert");
    }

    private Course convertTrack(TrkType trkType, String alternativeName) {
        String name = trkType.getName()==null || trkType.getName().isEmpty()? alternativeName : trkType.getName();
        List<CoursePoint> points = new ArrayList<>();
        for (TrksegType trkSeg : trkType.getTrkseg()) {
            for (WptType wptType : trkSeg.getTrkpt()) {
                points.add(wayPointToCoursePoint(wptType));
            }
        }
        return new Course(name, points);
    }

    private Course convertRoute(RteType rteType, String alternativeName) {
        String name = rteType.getName() == null || rteType.getName().isEmpty() ? alternativeName : rteType.getName();
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
                wptType.getEle()==null?0d:wptType.getEle().doubleValue());
    }
}
