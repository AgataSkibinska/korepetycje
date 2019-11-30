package com.inz.korepetycje.repository;

import com.inz.korepetycje.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
