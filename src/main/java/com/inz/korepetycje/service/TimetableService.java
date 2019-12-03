package com.inz.korepetycje.service;

import com.inz.korepetycje.exception.ResourceNotFoundException;
import com.inz.korepetycje.model.ActivityTime;
import com.inz.korepetycje.model.TimeSlot;
import com.inz.korepetycje.model.User;
import com.inz.korepetycje.payload.timetable.DayWithTimeSlotsResponse;
import com.inz.korepetycje.payload.timetable.TimetableResponse;
import com.inz.korepetycje.payload.timetable.TimetableSlotsRequest;
import com.inz.korepetycje.repository.ActivityTimeRepository;
import com.inz.korepetycje.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimetableService {

    private ActivityTimeRepository activityTimeRepository;
    private UserRepository userRepository;

    public TimetableService(ActivityTimeRepository activityTimeRepository, UserRepository userRepository) {
        this.activityTimeRepository = activityTimeRepository;
        this.userRepository = userRepository;
    }

    public ActivityTime createTimetable(TimetableSlotsRequest timetableSlotsRequest) {
        ActivityTime activityTime = new ActivityTime();
        activityTime.setStartTime(timetableSlotsRequest.getStartTime());
        activityTime.setEndDate(timetableSlotsRequest.getEndTime());

        List<TimeSlot> timeSlots = timetableSlotsRequest
            .getAvailableSlots().stream()
            .map(timeSlotRequest -> new TimeSlot(timeSlotRequest.getStartTime(),
                timeSlotRequest.getDuration(),
                timeSlotRequest.getDayOfWeek(),
                activityTime))
            .collect(Collectors.toList());

        activityTime.setTimeSlots(timeSlots);

        return activityTimeRepository.save(activityTime);
    }

    public TimetableResponse getAllAvailableTimeSlotsPerPeriod(String username, int periodInDays) {
        ActivityTime activityTime = getActivityTimeByUser(username);
        List<TimeSlot> allTimeSlots = activityTime.getTimeSlots();
        List<TimeSlot> freeSlots = allTimeSlots.stream()
            .filter(TimeSlot::getFree)
            .collect(Collectors.toList());

        LocalDateTime startDate = getStartingDate(activityTime);
        LocalDateTime endDate = startDate.plusDays(periodInDays);

        Map<LocalDate, List<LocalTime>> timeSlotsByDay = createTimeSlotsByDayMapping(freeSlots, endDate, startDate);

        Set<Map.Entry<LocalDate, List<LocalTime>>> entries = timeSlotsByDay.entrySet();
        List<DayWithTimeSlotsResponse> dayWithTimeSlots = entries.stream()
            .map(entry -> new DayWithTimeSlotsResponse(entry.getKey(), entry.getValue()))
            .sorted(((o1, o2) -> o1.getDay().isBefore(o2.getDay())?-1:1))
            .collect(Collectors.toList());

        TimetableResponse response = new TimetableResponse(startDate, endDate, dayWithTimeSlots);

        return response;
    }
    private Map<LocalDate, List<LocalTime>> createTimeSlotsByDayMapping(List<TimeSlot> freeSlots, LocalDateTime endDate, LocalDateTime current) {
        Map<LocalDate, List<LocalTime>> timeSlotsByDay = new HashMap<>();
        while (current.isBefore(endDate)) {
            LocalDate day = current.toLocalDate();
            List<TimeSlot> timeSlots = getTimeSlots(freeSlots, current);
            List<LocalTime> times = timeSlots.stream().map(TimeSlot::getStartTime).collect(Collectors.toList());
            if (!timeSlots.isEmpty()) {
                timeSlotsByDay.put(day, times);
            }
            current = current.plusDays(1);
        }
        return timeSlotsByDay;
    }

    private List<TimeSlot> getTimeSlots(List<TimeSlot> freeSlots, LocalDateTime current) {
        int dayOfWeek = current.getDayOfWeek().getValue(); //1- monday, 2 - tuesday, ...
        return freeSlots.stream()
            .filter(timeSlot -> timeSlot.getDayOfWeek() == dayOfWeek)
            .collect(Collectors.toList());
    }

    private LocalDateTime getStartingDate(ActivityTime activityTime) {
        LocalDateTime startDate = activityTime.getStartTime();
        if (startDate.isBefore(LocalDateTime.now())) {
            startDate = LocalDateTime.now();
        }
        return startDate;
    }

    private ActivityTime getActivityTimeByUser(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return activityTimeRepository.findByCreatedBy(user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("ActiveTime", "userId", user.getId()));
    }
}
