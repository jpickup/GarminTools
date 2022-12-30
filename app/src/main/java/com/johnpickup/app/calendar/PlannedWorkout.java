package com.johnpickup.app.calendar;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class PlannedWorkout {
    private final String name;
    private final String description;
    private final long offset;
}
