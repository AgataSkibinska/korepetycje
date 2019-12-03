package com.inz.korepetycje.service;

import com.inz.korepetycje.exception.BadRequestException;
import com.inz.korepetycje.exception.ResourceNotFoundException;
import com.inz.korepetycje.model.*;
import com.inz.korepetycje.model.advertisement.*;
import com.inz.korepetycje.payload.advertisement.AdvertisementRequest;
import com.inz.korepetycje.payload.advertisement.AdvertisementResponse;
import com.inz.korepetycje.payload.PagedResponse;
import com.inz.korepetycje.repository.AdvertisementRepository;
import com.inz.korepetycje.repository.SubjectRepository;
import com.inz.korepetycje.repository.UserRepository;
import com.inz.korepetycje.security.UserPrincipal;
import com.inz.korepetycje.utils.AppConstants;
import com.inz.korepetycje.utils.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    private UserRepository userRepository;
    private AdvertisementRepository advertisementRepository;
    private SubjectRepository subjectRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);

    public AdvertisementService(UserRepository userRepository, AdvertisementRepository advertisementRepository, SubjectRepository subjectRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
        this.subjectRepository = subjectRepository;
    }

    public PagedResponse<AdvertisementResponse> getAllAdvertisements(int page, int size) {
        validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Advertisement> ads = advertisementRepository.findAll(pageable);

        if (ads.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(),
                ads.getNumber(),
                ads.getSize(),
                ads.getTotalElements(),
                ads.getTotalPages(),
                ads.isLast());
        }

        Map<Long, User> creatorMap = getAdvertisementCreatorMap(ads.getContent());

        List<AdvertisementResponse> adsResponses = ads
            .map(ad -> ModelMapper.mapAdvertisementToAdvertisementResponse(ad, creatorMap.get(ad.getCreatedBy())))
            .getContent();

        return new PagedResponse<>(adsResponses, ads.getNumber(), ads.getSize(), ads.getTotalElements(), ads.getTotalPages(), ads.isLast());

    }

    public Advertisement createAdvertisement(AdvertisementRequest advertisementRequest) {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(advertisementRequest.getTitle());
        advertisement.setDescription(advertisementRequest.getDescription());
        advertisement.setDurationInMinutes(advertisementRequest.getDurationInMinutes());
        advertisement.setPrice(advertisementRequest.getPrice());
        advertisement.setTutoring(advertisementRequest.getTutoring());
        advertisement.setLocations(advertisementRequest.getLocations());

        advertisement.setCurriculumRange(CurriculumRangeName.valueOf(advertisementRequest.getCurriculumRange()));
        advertisement.setLessonLocationType(LessonLocationTypeName.valueOf(advertisementRequest.getLessonLocationType()));

        Subject subject = subjectRepository.findSubjectByName(
            advertisementRequest.getSubject())
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "name", advertisementRequest.getSubject()));


        advertisement.setSubject(subject);
        advertisement.setCreatedAt(Instant.now());

        return advertisementRepository.save(advertisement);
    }

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    private Map<Long, User> getAdvertisementCreatorMap(List<Advertisement> advertisements) {

        List<Long> creatorIds = advertisements.stream()
            .map(Advertisement::getCreatedBy)
            .distinct()
            .collect(Collectors.toList());

        List<User> creators = userRepository.findByIdIn(creatorIds);
        Map<Long, User> creatorMap = creators.stream()
            .collect(Collectors.toMap(User::getId, Function.identity()));

        return creatorMap;
    }

    public AdvertisementResponse getAdvertisementById(UserPrincipal currentUser,
                                                      Long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
            .orElseThrow(() -> new ResourceNotFoundException("Advertisement", "id", advertisementId));
        User creator = userRepository.findById(advertisement.getCreatedBy())
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", advertisement.getCreatedBy()));
        return ModelMapper.mapAdvertisementToAdvertisementResponse(advertisement, creator);
    }

    public PagedResponse<AdvertisementResponse> getAdvertisementsCreatedBy(String username,
                                                                           Boolean tutoring,
                                                                           int page,
                                                                           int size) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Advertisement> ads = advertisementRepository.findByCreatedByAndTutoring(user.getId(),tutoring, pageable);


        if (ads.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(),
                ads.getNumber(),
                ads.getSize(),
                ads.getTotalElements(),
                ads.getTotalPages(),
                ads.isLast());
        }

        Map<Long, User> creatorMap = getAdvertisementCreatorMap(ads.getContent());

        List<AdvertisementResponse> adsResponses = ads
            .map(ad -> ModelMapper.mapAdvertisementToAdvertisementResponse(ad, user))
            .getContent();

        return new PagedResponse<>(adsResponses, ads.getNumber(), ads.getSize(), ads.getTotalElements(), ads.getTotalPages(), ads.isLast());
    }

    public Advertisement updateAdvertisement(UserPrincipal currentUser, Long advertisementId, AdvertisementRequest advertisementRequest) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
            .orElseThrow(() -> new ResourceNotFoundException("Advertisement", "id", advertisementId));

        User user = userRepository.findById(currentUser.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));

        if(!advertisement.getCreatedBy().equals(user.getId())){
            throw new BadRequestException("Operation not permitted");
        }
        advertisement.setTitle(advertisementRequest.getTitle());
        advertisement.setDescription(advertisementRequest.getDescription());
        advertisement.setDurationInMinutes(advertisementRequest.getDurationInMinutes());
        advertisement.setPrice(advertisementRequest.getPrice());

        advertisement.setCurriculumRange(CurriculumRangeName.valueOf(advertisementRequest.getCurriculumRange()));
        advertisement.setLessonLocationType(LessonLocationTypeName.valueOf(advertisementRequest.getLessonLocationType()));

        Subject subject = subjectRepository.findSubjectByName(
            advertisementRequest.getSubject())
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "name", advertisementRequest.getSubject()));

        advertisement.setSubject(subject);
        advertisement.setUpdatedAt(Instant.now());
        return advertisementRepository.save(advertisement);
    }

    public void deleteAdvertisement(UserPrincipal currentUser, Long advertisementId) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
            .orElseThrow(() -> new ResourceNotFoundException("Advertisement", "id", advertisementId));

        User user = userRepository.findById(currentUser.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));

        if(!advertisement.getCreatedBy().equals(user.getId())){
            throw new BadRequestException("Operation not permitted");
        }

        advertisementRepository.delete(advertisement);
    }

    public PagedResponse<AdvertisementResponse> getAllAdvertisementsByTutoring(int page, int size, Boolean tutoring) {
        validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Advertisement> ads = advertisementRepository.findAdvertisementsByTutoring(tutoring, pageable);

        if (ads.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(),
                ads.getNumber(),
                ads.getSize(),
                ads.getTotalElements(),
                ads.getTotalPages(),
                ads.isLast());
        }

        Map<Long, User> creatorMap = getAdvertisementCreatorMap(ads.getContent());

        List<AdvertisementResponse> adsResponses = ads
            .map(ad -> ModelMapper.mapAdvertisementToAdvertisementResponse(ad, creatorMap.get(ad.getCreatedBy())))
            .getContent();

        return new PagedResponse<>(adsResponses, ads.getNumber(), ads.getSize(), ads.getTotalElements(), ads.getTotalPages(), ads.isLast());

    }
}
