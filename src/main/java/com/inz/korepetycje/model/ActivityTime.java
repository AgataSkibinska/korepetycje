package com.inz.korepetycje.model;

import com.inz.korepetycje.model.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "activity_time")
public class ActivityTime extends UserDateAudit {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endDate;

    @OneToMany(mappedBy="activityTime",cascade = CascadeType.ALL)
    private List<TimeSlot> timeSlots;

    public ActivityTime () {

    }

    public ActivityTime(@NotNull LocalDateTime startTime, @NotNull LocalDateTime endDate) {
        this.startTime = startTime;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
