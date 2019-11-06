package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    Optional<Advertisement> findById(Long aLong);

    Page<Advertisement> findAdvertisementByUserId(Long userId, Pageable pageable);

    List<Advertisement> findAdvertisementsByIdIn(List<Long> advertisementsIds);

    List<Advertisement> findAdvertisementsByIdIn(List<Long>advertisementsIds, Sort sort);

    List<Advertisement> findAdvertisementsBySubjectId(Long subjectId);
}
