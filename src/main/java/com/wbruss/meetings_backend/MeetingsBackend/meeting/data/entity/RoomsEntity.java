package com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomsEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "block", nullable = false)
    private String block;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "room_id")
    private List<MeetingsEntity> meetings;

    public RoomsEntity() {
    }

    public RoomsEntity(String name, String block, int capacity) {
        this.name = name;
        this.block = block;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room {" +
                "id: " + id +
                ", name: " + name +
                ", block: " + block +
                ", capacity: " + capacity +
                "}";
    }
}
