package com.inz.korepetycje.payload.timetable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DayWithTimeSlotsResponse {
    private LocalDate day;
    private List<LocalTime> timeSlots;

    public DayWithTimeSlotsResponse(LocalDate day, List<LocalTime> timeSlots) {
        this.day = day;
        this.timeSlots = timeSlots;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public List<LocalTime> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<LocalTime> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
