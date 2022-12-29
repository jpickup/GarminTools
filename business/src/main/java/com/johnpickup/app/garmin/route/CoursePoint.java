package com.johnpickup.app.garmin.route;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CoursePoint {
    private final double latitude;
    private final double longitude;
    private final double altitude;
}
