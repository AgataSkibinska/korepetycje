package com.inz.korepetycje.model.advertisement;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject_categories")
public class SubjectCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private SubjectCategoryName name;

    @OneToMany(mappedBy = "category")
    private List<Subject> subjects = new ArrayList<>();

    public SubjectCategory() {
    }

    public SubjectCategory(SubjectCategoryName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubjectCategoryName getName() {
        return name;
    }

    public void setName(SubjectCategoryName name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
