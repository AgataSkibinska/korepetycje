package com.inz.korepetycje.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "lesson_locations")
public class LessonLocationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private LessonLocationTypeName name;

    public LessonLocationType() {
    }

    public LessonLocationType(LessonLocationTypeName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LessonLocationTypeName getName() {
        return name;
    }

    public void setName(LessonLocationTypeName name) {
        this.name = name;
    }
}
