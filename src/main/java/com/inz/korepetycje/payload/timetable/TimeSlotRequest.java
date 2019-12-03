package com.inz.korepetycje.payload.timetable;

import java.time.LocalTime;

public class TimeSlotRequest {
    private LocalTime startTime; //12:30
    private int dayOfWeek; //Monday
    private int duration;

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDuration() {
        return duration;
    }
}
