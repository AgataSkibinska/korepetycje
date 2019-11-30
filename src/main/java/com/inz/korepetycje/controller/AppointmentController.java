package com.inz.korepetycje.controller;


import com.inz.korepetycje.model.advertisement.Advertisement;
import com.inz.korepetycje.payload.AdvertisementReply;
import com.inz.korepetycje.payload.ApiResponse;
import com.inz.korepetycje.repository.AdvertisementRepository;
import com.inz.korepetycje.repository.AppointmentRepository;
import com.inz.korepetycje.repository.UserRepository;
import com.inz.korepetycje.security.CurrentUser;
import com.inz.korepetycje.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private AdvertisementRepository advertisementRepository;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    public AppointmentController(AppointmentRepository appointmentRepository,
                                 UserRepository userRepository,
                                 AdvertisementRepository advertisementRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @PostMapping("/{advertisementId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAppointment(@PathVariable Long advertisementId,
                                               @RequestBody AdvertisementReply advertisementReply,
                                               @CurrentUser UserPrincipal currentUser){


        return ResponseEntity.ok()
            .body(new ApiResponse(true, "Advertisement Created Successfully"));
    }
}
