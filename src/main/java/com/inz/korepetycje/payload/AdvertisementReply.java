package com.inz.korepetycje.payload;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AdvertisementReply {

    @NotNull
    private Boolean isWeekly;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    public AdvertisementReply(@NotNull Boolean isWeekly, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime) {
        this.isWeekly = isWeekly;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Boolean getWeekly() {
        return isWeekly;
    }

    public void setWeekly(Boolean weekly) {
        isWeekly = weekly;
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
}
