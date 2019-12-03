package com.inz.korepetycje.payload.timetable;

import java.time.LocalDateTime;
import java.util.List;

//do tworzenia terminarza dostepnych terminów
public class TimetableSlotsRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private List<TimeSlotRequest> availableSlots;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return duration;
    }

    public List<TimeSlotRequest> getAvailableSlots() {
        return availableSlots;
    }
}
