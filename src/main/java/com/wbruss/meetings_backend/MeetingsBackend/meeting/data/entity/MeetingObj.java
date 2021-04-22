package com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity;

public class MeetingObj extends MeetingsEntity{
    private long id;
    private String venue;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public MeetingObj() {
    }

    public MeetingObj(String venue) {
        this.venue = venue;
    }

//    public MeetingObj(String title, LocalDate date, LocalTime time, LocalTime duration, Long room_id, String description, Long id, String venue) {
//        super(title, date, time, duration, room_id, description,venue);
//        this.id = id;
//        this.venue = venue;
//    }


}
