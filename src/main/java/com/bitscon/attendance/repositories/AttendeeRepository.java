package com.bitscon.attendance.repositories;

import com.bitscon.attendance.model.Attendee;
import com.bitscon.attendance.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Attendee findByFNameAndMInitialAndLNameAndNumberAndSchool(String FName, String MInitial, String LName, String Number, String School);
    List<Attendee> findAllBySchoolAndDateId(String School, Date dateId);
    List<Attendee> findAllBySchool(String school);
    List<Attendee> findAllByDate(java.util.Date date);
}
