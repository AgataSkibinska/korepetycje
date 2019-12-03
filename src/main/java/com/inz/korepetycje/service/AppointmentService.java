package com.inz.korepetycje.service;

import com.inz.korepetycje.exception.ResourceNotFoundException;
import com.inz.korepetycje.model.Appointment;
import com.inz.korepetycje.model.AppointmentStatus;
import com.inz.korepetycje.model.User;
import com.inz.korepetycje.model.advertisement.Advertisement;
import com.inz.korepetycje.payload.advertisement.AdvertisementReply;
import com.inz.korepetycje.repository.*;
import com.inz.korepetycje.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private UserRepository userRepository;
    private AppointmentRepository appointmentRepository;
    private ActivityTimeRepository activityTimeRepository;
    private TimeSlotRepository timeSlotRepository;
    private AdvertisementRepository advertisementRepository;


    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    public AppointmentService(UserRepository userRepository, AppointmentRepository appointmentRepository, ActivityTimeRepository activityTimeRepository, TimeSlotRepository timeSlotRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.activityTimeRepository = activityTimeRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public Appointment createAppointment(Long advertisementId,
                                         AdvertisementReply advertisementReply,
                                         UserPrincipal currentUser) {

        User student = userRepository.findByUsername(currentUser.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", currentUser.getUsername()));
        Appointment appointment = new Appointment();
        appointment.setStartDate(advertisementReply.getStartTime());
        appointment.setEndDate(advertisementReply.getEndTime());
        appointment.setWeekly(advertisementReply.getWeekly());
        appointment.setStudent(student);

        Advertisement advertisement = advertisementRepository.findById(advertisementId)
            .orElseThrow(() -> new ResourceNotFoundException("Advertisement", "id", advertisementId));
        appointment.setAdvertisement(advertisement);

        return appointmentRepository.save(appointment);
    }

    public Appointment acceptOrDecline(Long appointmentId,
                                       Boolean acceptedOrDeclined){
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", appointmentId));
        appointment.setStatus(acceptedOrDeclined ? AppointmentStatus.APPROVED : AppointmentStatus.REFUSED);

        return appointmentRepository.save(appointment);
    }
}
