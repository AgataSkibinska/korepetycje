package com.inz.korepetycje.controller;

import com.inz.korepetycje.payload.AdvertisementResponse;
import com.inz.korepetycje.payload.PagedResponse;
import com.inz.korepetycje.repository.AdvertisementRepository;
import com.inz.korepetycje.repository.UserRepository;
import com.inz.korepetycje.security.CurrentUser;
import com.inz.korepetycje.security.UserPrincipal;
import com.inz.korepetycje.service.AdvertisementService;
import com.inz.korepetycje.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {

    private AdvertisementRepository advertisementRepository;
    private UserRepository userRepository;
    private AdvertisementService advertisementService;

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    public AdvertisementController(AdvertisementRepository advertisementRepository,
                                   UserRepository userRepository,
                                   AdvertisementService advertisementService) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public PagedResponse<AdvertisementResponse> getAdvertisements(@CurrentUser UserPrincipal currentUser,
                                                                  @RequestParam(value = "page",
                                                                      defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                  @RequestParam(value = "size",
                                                                      defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
                                                                  ) {
        return

    }
}
