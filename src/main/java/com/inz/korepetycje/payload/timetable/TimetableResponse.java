package com.inz.korepetycje.payload.timetable;

import java.time.LocalDateTime;
import java.util.List;

public class TimetableResponse {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<DayWithTimeSlotsResponse> timeSlots;

    public TimetableResponse(LocalDateTime startTime, LocalDateTime endTime, List<DayWithTimeSlotsResponse> timeSlots) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeSlots = timeSlots;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<DayWithTimeSlotsResponse> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<DayWithTimeSlotsResponse> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
