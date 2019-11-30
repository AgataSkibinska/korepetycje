package com.inz.korepetycje.controller;

import com.inz.korepetycje.model.advertisement.Advertisement;
import com.inz.korepetycje.payload.AdvertisementRequest;
import com.inz.korepetycje.payload.AdvertisementResponse;
import com.inz.korepetycje.payload.ApiResponse;
import com.inz.korepetycje.payload.PagedResponse;
import com.inz.korepetycje.repository.AdvertisementRepository;
import com.inz.korepetycje.repository.UserRepository;
import com.inz.korepetycje.security.CurrentUser;
import com.inz.korepetycje.security.UserPrincipal;
import com.inz.korepetycje.service.AdvertisementService;
import com.inz.korepetycje.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAdvertisement(@Valid @RequestBody AdvertisementRequest advertisementRequest) {
        Advertisement advertisement = advertisementService.createAdvertisement(advertisementRequest);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{adId}")
            .buildAndExpand(advertisement.getId()).toUri();

        return ResponseEntity.created(location)
            .body(new ApiResponse(true, "Advertisement Created Successfully"));
    }

    @GetMapping
    public PagedResponse<AdvertisementResponse> getAdvertisements(@RequestParam(value = "page",
                                                                      defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                  @RequestParam(value = "size",
                                                                      defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
                                                                  ) {
        return advertisementService.getAllAdvertisements(page, size);
    }

    @GetMapping("/{advertisementId}")
    public AdvertisementResponse getAdvertisementById(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long advertisementId) {
        return advertisementService.getAdvertisementById(currentUser, advertisementId);
    }

    @PutMapping("/{advertisementId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateAdvertisement(@CurrentUser UserPrincipal currentUser,
                                                 @PathVariable Long advertisementId,
                                                 @Valid @RequestBody AdvertisementRequest advertisementRequest){
        advertisementService.updateAdvertisement(currentUser, advertisementId, advertisementRequest);

        return ResponseEntity.ok()
            .body(new ApiResponse(true, "Advertisement Updated Successfully"));
    }

    @DeleteMapping("/{advertisementId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteAdvertisement(@CurrentUser UserPrincipal currentUser,
                                                 @PathVariable Long advertisementId) {
        advertisementService.deleteAdvertisement(currentUser, advertisementId);
        return ResponseEntity.ok().body(new ApiResponse(true, "Advertisement Deleted Successfully"));
    }

    @GetMapping("/tutor")
    public PagedResponse<AdvertisementResponse> getTutorAdvertisements(@RequestParam(value = "page",
                                                                      defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                  @RequestParam(value = "size",
                                                                      defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    ) {
        return advertisementService.getAllAdvertisementsByTutoring(page, size, true );
    }

    @GetMapping("/student")
    public PagedResponse<AdvertisementResponse> getStudentAdvertisements(@RequestParam(value = "page",
                                                                           defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                       @RequestParam(value = "size",
                                                                           defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    ) {
        return advertisementService.getAllAdvertisementsByTutoring(page, size, false);
    }


}
