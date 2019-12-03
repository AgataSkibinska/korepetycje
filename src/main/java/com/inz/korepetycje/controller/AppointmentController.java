package com.inz.korepetycje.controller;

import com.inz.korepetycje.payload.advertisement.AdvertisementReply;
import com.inz.korepetycje.payload.ApiResponse;
import com.inz.korepetycje.security.CurrentUser;
import com.inz.korepetycje.security.UserPrincipal;
import com.inz.korepetycje.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/{advertisementId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createAppointment(@PathVariable Long advertisementId,
                                               @RequestBody AdvertisementReply advertisementReply,
                                               @CurrentUser UserPrincipal currentUser) {

        appointmentService.createAppointment(advertisementId, advertisementReply, currentUser);
        return ResponseEntity.ok()
            .body(new ApiResponse(true, "Advertisement Created Successfully"));
    }

    @PostMapping("/{appointmentId}/{acceptedOrDeclined}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> acceptOrDeclineAppointment(@PathVariable Long appointmentId,
                                                        @PathVariable Boolean acceptedOrDeclined) {

        appointmentService.acceptOrDecline(appointmentId, acceptedOrDeclined);
        return ResponseEntity.ok()
            .body(new ApiResponse(true, "Advertisement Created Successfully"));
    }




}
