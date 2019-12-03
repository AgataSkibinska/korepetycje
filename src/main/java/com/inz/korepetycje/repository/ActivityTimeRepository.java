package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.ActivityTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityTimeRepository extends JpaRepository<ActivityTime, Long> {
    Optional<ActivityTime> findByCreatedBy(Long id);
}
