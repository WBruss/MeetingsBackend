package com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "meetings")
public class MeetingsEntity {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private LocalTime duration;

    @Column(nullable = false)
    private LocalDateTime time_created;

    @Column(nullable = false)
    private String description;

//    @Transient
    @ManyToOne
    @JoinColumn(name = "venue", nullable = false)
    private RoomsEntity room_id;



    public MeetingsEntity() {
    }
    public MeetingsEntity(String title, LocalDate date, LocalTime time, LocalTime duration, RoomsEntity room_id, String description) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.room_id = room_id;
        this.description = description;
        this.time_created = LocalDateTime.now();
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

    public LocalDateTime getTime_created() {
        return time_created;
    }

    public void setTime_created(LocalDateTime time_created) {
        this.time_created = time_created;
    }

    public RoomsEntity getRoom_id() {
        return room_id;
    }

    public void setRoom_id(RoomsEntity room_id) {
        this.room_id = room_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return ("Meeting {" +
                "id: " + id +
                ", title: " + title +
                ", date: " + date +
                ", time: " + time +
                ", duration: " + duration +
                ", description: " + description +
                ", room_id: " + room_id +
                "}");
    }
}
