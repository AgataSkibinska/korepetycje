package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> findAllByUser(Long userId);
}
