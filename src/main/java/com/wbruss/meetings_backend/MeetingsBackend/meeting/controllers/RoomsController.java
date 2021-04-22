package com.wbruss.meetings_backend.MeetingsBackend.meeting.controllers;

import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity.RoomsEntity;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.repository.jpa.RoomsRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RoomsController {

    @Autowired
    RoomsRepositoryJPA roomsRepository;

    @GetMapping("/rooms")
    public ResponseEntity getRooms(){
        System.out.println("**********  GET ROOMS ***********");
        return ResponseEntity.ok(roomsRepository.findAll());
    }

    @PostMapping("/rooms")
    public ResponseEntity addNewRoom(@RequestBody RoomsEntity roomsEntity){
        System.out.println(roomsEntity);
        return ResponseEntity.ok(roomsRepository.save(roomsEntity));
    }

    @PutMapping("/rooms")
    public ResponseEntity updateRoom(@RequestParam(value = "id") long id, @RequestParam(value = "name") String name, @RequestParam(value = "block") String block, @RequestParam(value = "capacity") int capacity){
        Optional<RoomsEntity> optionalRoomsEntity = roomsRepository.findById(id);
        if(!optionalRoomsEntity.isPresent()){
            return ResponseEntity.badRequest().body("No Room with specified id " + id + " found!");
        }
        RoomsEntity foundRoom = optionalRoomsEntity.get();
        foundRoom.setName(name);
        foundRoom.setBlock(block);
        foundRoom.setCapacity(capacity);
        return ResponseEntity.ok(roomsRepository.save(foundRoom));
    }

    @DeleteMapping("/rooms")
    public ResponseEntity deleteRoom(@RequestParam(value = "id") Long id){
        Optional<RoomsEntity> optionalRoomsEntity = roomsRepository.findById(id);
        if(!optionalRoomsEntity.isPresent()){
            return ResponseEntity.badRequest().body("No Room with specified id " + id + " found!");
        }
        roomsRepository.deleteById(id);
        return ResponseEntity.ok(optionalRoomsEntity);
    }
}
