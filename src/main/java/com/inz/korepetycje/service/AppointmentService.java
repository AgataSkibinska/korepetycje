package com.inz.korepetycje.service;

import com.inz.korepetycje.model.Appointment;
import com.inz.korepetycje.payload.AdvertisementReply;
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


    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    public AppointmentService(UserRepository userRepository,
                              AppointmentRepository appointmentRepository,
                              ActivityTimeRepository activityTimeRepository,
                              TimeSlotRepository timeSlotRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.activityTimeRepository = activityTimeRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public Appointment createAppointment(Long advertisementId,
                                         AdvertisementReply advertisementReply,
                                         UserPrincipal currentUser) {
        Appointment appointment = new Appointment();
        appointment.setStartDate(advertisementReply.getStartTime());
        appointment.setEndDate(advertisementReply.getEndTime());
        appointment.setWeekly(advertisementReply.getWeekly());

        return appointmentRepository.save(appointment);
    }
}
