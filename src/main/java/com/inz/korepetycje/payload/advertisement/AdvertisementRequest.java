package com.inz.korepetycje.payload.advertisement;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class AdvertisementRequest {

    @NotBlank
    @Size(max = 140)
    private String title;

    @NotBlank
    private String description;


    private double price;


    private int durationInMinutes;

    @NotBlank
    private String subject;

    private String curriculumRange;

    private String lessonLocationType;

    private Boolean tutoring;

    private List<String> locations;

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

    public Boolean getTutoring() {
        return tutoring;
    }

    public void setTutoring(Boolean tutoring) {
        this.tutoring = tutoring;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
