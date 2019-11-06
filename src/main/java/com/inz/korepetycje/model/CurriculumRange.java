package com.inz.korepetycje.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "curriculum_ranges")
public class CurriculumRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private CurriculumRangeName name;

    public CurriculumRange() {
    }

    public CurriculumRange(CurriculumRangeName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurriculumRangeName getName() {
        return name;
    }

    public void setName(CurriculumRangeName name) {
        this.name = name;
    }
}
