package com.inz.korepetycje.controller;

import com.inz.korepetycje.exception.ResourceNotFoundException;
import com.inz.korepetycje.model.User;
import com.inz.korepetycje.payload.*;
import com.inz.korepetycje.payload.advertisement.AdvertisementResponse;
import com.inz.korepetycje.payload.authentication.UserIdentityAvailability;
import com.inz.korepetycje.payload.authentication.UserProfile;
import com.inz.korepetycje.payload.authentication.UserSummary;
import com.inz.korepetycje.payload.opinion.OpinionRequest;
import com.inz.korepetycje.payload.opinion.OpinionResponse;
import com.inz.korepetycje.payload.timetable.TimetableResponse;
import com.inz.korepetycje.payload.timetable.TimetableSlotsRequest;
import com.inz.korepetycje.repository.AdvertisementRepository;
import com.inz.korepetycje.repository.UserRepository;
import com.inz.korepetycje.security.CurrentUser;
import com.inz.korepetycje.security.UserPrincipal;
import com.inz.korepetycje.service.AdvertisementService;
import com.inz.korepetycje.service.OpinionService;
import com.inz.korepetycje.service.TimetableService;
import com.inz.korepetycje.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;
    private AdvertisementRepository advertisementRepository;
    private AdvertisementService advertisementService;
    private OpinionService opinionService;
    private TimetableService timetableService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserRepository userRepository, AdvertisementRepository advertisementRepository, AdvertisementService advertisementService, OpinionService opinionService, TimetableService timetableService) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
        this.advertisementService = advertisementService;
        this.opinionService = opinionService;
        this.timetableService = timetableService;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getCreatedAt());

        return userProfile;
    }

    @GetMapping("/users/{userName}/tutorAdvertisements")
    public PagedResponse<AdvertisementResponse> getTutorAdvertisementsCreatedBy(@PathVariable(value = "userName") String username,
                                                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return advertisementService.getAdvertisementsCreatedBy(username,true, page, size);
    }

    @GetMapping("/users/{userName}/studentAdvertisements")
    public PagedResponse<AdvertisementResponse> getStudentAdvertisementsCreatedBy(@PathVariable(value = "userName") String username,
                                                                                @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return advertisementService.getAdvertisementsCreatedBy(username,false, page, size);
    }

    @GetMapping("/users/{userName}/opinions")
    public List<OpinionResponse> getOpinionsOfUser(@PathVariable(value = "userName") String username){
        return opinionService.getOpinionsOf(username);
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOpinion(@RequestBody OpinionRequest opinionRequest) {
        opinionService.createOpinion(opinionRequest);
        return ResponseEntity.ok()
            .body(new ApiResponse(true, "Advertisement Created Successfully"));
    }

    @PostMapping("/user/timetable")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createTimeTable(@RequestBody TimetableSlotsRequest timetableSlotsRequest) {
        timetableService.createTimetable(timetableSlotsRequest);
        return ResponseEntity.ok()
            .body(new ApiResponse(true, "Timetable Created Successfully"));
    }

    @GetMapping("/users/{userName}/timetable")
    public TimetableResponse getAllAvailableTimeSlots(@PathVariable(value = "userName") String username,
                                                      @RequestParam(value = "days",
                                                          defaultValue = AppConstants.DEFAULT_DAYS_RANGE) int days){
        return timetableService.getAllAvailableTimeSlotsPerPeriod(username, days);
    }



}
