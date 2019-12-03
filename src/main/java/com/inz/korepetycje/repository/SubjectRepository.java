package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.advertisement.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findSubjectByName(String name);
    List<Subject> findAll();
}
