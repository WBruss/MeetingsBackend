package com.wbruss.meetings_backend.MeetingsBackend.meeting.data.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MeetingsEntityDto {
    private long id;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private  LocalTime duration;
    private String venue;
    private String description;

    public  MeetingsEntityDto(){}

    public MeetingsEntityDto(long id, String title, LocalDate date, LocalTime time, LocalTime duration, String venue, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.venue = venue;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
