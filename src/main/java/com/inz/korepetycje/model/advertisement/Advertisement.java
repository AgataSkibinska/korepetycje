package com.inz.korepetycje.model.advertisement;

import com.inz.korepetycje.model.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "advertisements")
//    , uniqueConstraints = {
//    @UniqueConstraint(columnNames = {
//        "user_id"
//    })
//})
public class Advertisement extends UserDateAudit {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String title;

    @NotBlank
    private String description;

    private double price;

    private int durationInMinutes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    private CurriculumRangeName curriculumRange;

    private LessonLocationTypeName lessonLocationType;

    @NotNull
    private Boolean tutoring;

    @ElementCollection
    private List<String> locations = new ArrayList<>();


    public Advertisement() {
    }

    public Advertisement(@NotBlank @Size(max = 140) String title,
                         @NotBlank String description,
                         double price,
                         int durationInMinutes,
                         Subject subject,
                         CurriculumRangeName curriculumRange,
                         LessonLocationTypeName lessonLocationType,
                         Boolean tutoring) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.durationInMinutes = durationInMinutes;
        this.subject = subject;
        this.curriculumRange = curriculumRange;
        this.lessonLocationType = lessonLocationType;
        this.tutoring = tutoring;
    }

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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public CurriculumRangeName getCurriculumRange() {
        return curriculumRange;
    }

    public void setCurriculumRange(CurriculumRangeName curriculumRange) {
        this.curriculumRange = curriculumRange;
    }

    public LessonLocationTypeName getLessonLocationType() {
        return lessonLocationType;
    }

    public void setLessonLocationType(LessonLocationTypeName lessonLocationType) {
        this.lessonLocationType = lessonLocationType;
    }

    public Boolean getTutoring() {
        return tutoring;
    }

    public void setTutoring(Boolean tutoring) {
        this.tutoring = tutoring;
    }
}
