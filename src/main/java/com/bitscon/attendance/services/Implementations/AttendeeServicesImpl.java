package com.bitscon.attendance.services.Implementations;

import com.bitscon.attendance.model.Attendee;
import com.bitscon.attendance.repositories.AttendeeRepository;
import com.bitscon.attendance.repositories.DateRepository;
import com.bitscon.attendance.services.AttendeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServicesImpl implements AttendeeServices {
    private final AttendeeRepository attendeeRepository;
    private final DateRepository dateRepository;

    @Override
    public Attendee AddAttendance(Attendee newAttendee) {
        // get attendee if exists
        Attendee oldAttendee = attendeeRepository.findByFNameAndMInitialAndLNameAndNumberAndSchool(newAttendee.getFName(), newAttendee.getMInitial(), newAttendee.getLName(), newAttendee.getNumber(), newAttendee.getSchool());

        //if attendee doesn't exist, save new instance of this attendee and get the attendee instance
        if (oldAttendee == null) {
            newAttendee.setDateId(new ArrayList<>());
            oldAttendee = attendeeRepository.save(newAttendee);
        }

        //get current Date data
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        com.bitscon.attendance.model.Date lastRecord = dateRepository.findFirstByOrderByDateIDDesc();
        if (lastRecord == null || currentDate.compareTo(lastRecord.getDate()) != 0){
            com.bitscon.attendance.model.Date newDate = new com.bitscon.attendance.model.Date();
            newDate.setDate(currentDate);
            lastRecord = dateRepository.save(newDate);
        }

        //save current Date data
        oldAttendee.getDateId().add(lastRecord);

        //save attendee
        return attendeeRepository.save(oldAttendee);
    }

    @Override
    public List<Attendee> viewAllAttendance() {
        return attendeeRepository.findAll();
    }

    @Override
    public List<Attendee> viewFilteredAttendance(String schoolFilter, String dateFilter) {
        return attendeeRepository.findAllBySchoolAndDateId(schoolFilter, new com.bitscon.attendance.model.Date());
    }
}
