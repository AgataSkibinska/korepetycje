package com.inz.korepetycje.model;

import com.inz.korepetycje.model.audit.UserDateAudit;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "advertisements", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "user_id"
    })
})
public class Advertisement extends UserDateAudit {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String title;

    @NotBlank
    @Lob
    private String description;

    @NonNull
    private double price;

    @NonNull
    private int durationInMinutes;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curriculumRange_id", nullable = false)
    private CurriculumRange curriculumRange;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lessonLocationType_id", nullable = false)
    private LessonLocationType lessonLocationType;

    //TODO: dodać lokalizację, jakieś api czy cos


    public Advertisement() {
    }

    public Advertisement(@NotBlank @Size(max = 140) String title,
                         @NotBlank String description,
                         double price,
                         int durationInMinutes,
                         User user,
                         Subject subject,
                         CurriculumRange curriculumRange,
                         LessonLocationType lessonLocationType) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.durationInMinutes = durationInMinutes;
        this.user = user;
        this.subject = subject;
        this.curriculumRange = curriculumRange;
        this.lessonLocationType = lessonLocationType;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public CurriculumRange getCurriculumRange() {
        return curriculumRange;
    }

    public void setCurriculumRange(CurriculumRange curriculumRange) {
        this.curriculumRange = curriculumRange;
    }

    public LessonLocationType getLessonLocationType() {
        return lessonLocationType;
    }

    public void setLessonLocationType(LessonLocationType lessonLocationType) {
        this.lessonLocationType = lessonLocationType;
    }
}
