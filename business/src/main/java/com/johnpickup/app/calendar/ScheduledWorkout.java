package com.johnpickup.app.calendar;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class ScheduledWorkout {
    private final String name;
    private final String description;
    private final LocalDate date;
}
