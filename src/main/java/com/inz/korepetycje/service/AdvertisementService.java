package com.inz.korepetycje.service;

import com.inz.korepetycje.exception.BadRequestException;
import com.inz.korepetycje.payload.AdvertisementResponse;
import com.inz.korepetycje.payload.PagedResponse;
import com.inz.korepetycje.repository.AdvertisementRepository;
import com.inz.korepetycje.repository.UserRepository;
import com.inz.korepetycje.security.UserPrincipal;
import com.inz.korepetycje.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {

    private UserRepository userRepository;
    private AdvertisementRepository advertisementRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);

    public AdvertisementService(UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public PagedResponse<AdvertisementResponse> getAllAdvertisements(UserPrincipal currentUser, int page, int size){
        validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, )
    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
