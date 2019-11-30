package com.inz.korepetycje.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "activity_time")
public class ActivityTime {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "activityTime")
    private User user;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endDate;

    @OneToMany(mappedBy="activityTime")
    private Set<TimeSlot> timeSlots;

    ActivityTime () {

    }

    public ActivityTime(User user, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endDate) {
        this.user = user;
        this.startTime = startTime;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
