package com.inz.korepetycje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "time_slots")
public class TimeSlot {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private int duration;

    @NotNull
    private int dayOfWeek; // 1 - poniedzielak, 7 - niedziela

    @ManyToOne
    @JoinColumn(name="activityTime_id", nullable=false)
    private ActivityTime activityTime;

    public TimeSlot() {
    }

    public TimeSlot(@NotNull LocalTime startTime, @NotNull int duration, @NotNull int dayOfWeek, ActivityTime activityTime) {
        this.startTime = startTime;
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
        this.activityTime = activityTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ActivityTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(ActivityTime activityTime) {
        this.activityTime = activityTime;
    }
}
