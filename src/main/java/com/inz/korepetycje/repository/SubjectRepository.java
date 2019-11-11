package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.Subject;
import com.inz.korepetycje.model.SubjectName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findSubjectByName(SubjectName name);
}
