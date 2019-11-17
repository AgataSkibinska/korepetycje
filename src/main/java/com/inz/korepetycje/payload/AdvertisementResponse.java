package com.inz.korepetycje.payload;

public class AdvertisementResponse {
    private Long id;
    private String title;
    private String description;
    private double price;
    private int durationInMinutes;
    private String subject;
    private String curriculumRange;
    private String lessonLocationType;
    private UserSummary createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public UserSummary getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserSummary createdBy) {
        this.createdBy = createdBy;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCurriculumRange() {
        return curriculumRange;
    }

    public void setCurriculumRange(String curriculumRange) {
        this.curriculumRange = curriculumRange;
    }

    public String getLessonLocationType() {
        return lessonLocationType;
    }

    public void setLessonLocationType(String lessonLocationType) {
        this.lessonLocationType = lessonLocationType;
    }
}
