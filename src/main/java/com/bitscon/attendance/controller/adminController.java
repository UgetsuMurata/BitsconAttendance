package com.bitscon.attendance.controller;


import com.bitscon.attendance.dto.Filter;
import com.bitscon.attendance.model.Attendee;
import com.bitscon.attendance.services.AttendeeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class adminController {
    private final AttendeeServices attendeeServices;

    @GetMapping("/attendance/view/all")
    public ResponseEntity<List<Attendee>> getAllAttendance(){
        return ResponseEntity.ok(attendeeServices.viewAllAttendance());
    }

    @GetMapping("/attendance/view/filtered")
    public ResponseEntity<List<Attendee>> getFilteredAttendance(@RequestBody Filter filter){
        return ResponseEntity.ok(attendeeServices.viewFilteredAttendance(filter.getSchool(), filter.getDate()));
    }
}
