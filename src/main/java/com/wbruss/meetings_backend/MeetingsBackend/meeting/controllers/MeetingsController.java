package com.wbruss.meetings_backend.MeetingsBackend.meeting.controllers;

import com.wbruss.meetings_backend.MeetingsBackend.meeting.services.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MeetingsController {

    @Autowired
    MeetingsService meetingsService;

    @GetMapping("/meetings")
    public ResponseEntity getAllMeetings() throws ParseException {
        System.out.println("**********  GET Meetings ***********");
        return  ResponseEntity.ok(meetingsService.getMeetings());
    }

    @GetMapping("/meetings/meeting")
    public ResponseEntity getMeetingById(@RequestParam(value = "id") Long id ) throws ParseException {
        System.out.println("********** Get Meeting ID: " + id +  " **********");
        return ResponseEntity.ok(meetingsService.getMeetingByID(id));
    }

    @PostMapping("/meetings")
    public ResponseEntity createMeeting(@RequestBody Map<String, String> meetingData) throws ParseException {
        System.out.println("*** Create Meeting ***");
        return ResponseEntity.ok(meetingsService.createMeetingService(meetingData));
    }

    @DeleteMapping("/meetings")
    public ResponseEntity deleteMeeting(@RequestParam(value = "id") Long id){
        System.out.println("*** Delete Meeting ***");
        return ResponseEntity.ok(meetingsService.deleteMeeting(id));
    }

}
