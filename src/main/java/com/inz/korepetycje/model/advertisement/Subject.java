package com.inz.korepetycje.model.advertisement;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(length = 60)
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_category")
    private SubjectCategory category;

    public Subject() {
    }

    public Subject(String name, SubjectCategory category) {
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectCategory getCategory() {
        return category;
    }

    public void setCategory(SubjectCategory category) {
        this.category = category;
    }
}
