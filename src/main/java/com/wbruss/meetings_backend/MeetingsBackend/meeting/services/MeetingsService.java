package com.wbruss.meetings_backend.MeetingsBackend.meeting.services;

import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.dto.MeetingsEntityDto;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.dto.MeetingsEntityDtoConverter;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity.MeetingsEntity;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity.RoomsEntity;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.repository.MeetingsRepository;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.repository.jpa.MeetingsRepositoryJPA;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.repository.jpa.RoomsRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetingsService implements MeetingsRepository {

    @Autowired
    MeetingsRepositoryJPA meetingsRepositoryJPA;
    @Autowired
    RoomsRepositoryJPA roomsRepositoryJPA;

    MeetingsEntityDtoConverter meetingsEntityDtoConverter;


    public List<MeetingsEntityDto> getMeetings() throws ParseException {

        System.out.println("******* Get Meetings *********\n");
        return (
                (List<MeetingsEntity>) meetingsRepositoryJPA
                .findAll())
                .stream()
                .map(this::meetingsEntityToMeetingsEntityDtoMapper)
                .collect(Collectors.toList()
                );
    }


    public MeetingsEntity getMeetingByID(long id) throws ParseException {
        Optional<MeetingsEntity> meetingsEntity = meetingsRepositoryJPA.findById(id);
        Optional<RoomsEntity> roomsEntity = roomsRepositoryJPA.findById(meetingsEntity.get().getRoom_id().getId());
        return meetingsEntity.get();
    }


    private MeetingsEntityDto meetingsEntityToMeetingsEntityDtoMapper(MeetingsEntity meetingsEntity){
        System.out.println("DTOing");
        MeetingsEntityDto meetingsEntityDto = new MeetingsEntityDto(
                meetingsEntity.getId(),
                meetingsEntity.getTitle(),
                meetingsEntity.getDate(),
                meetingsEntity.getTime(),
                meetingsEntity.getDuration(),
                meetingsEntity.getRoom_id().getName(),
                meetingsEntity.getDescription()
        );

        System.out.println("Converted: " + meetingsEntityDto.toString());
        return meetingsEntityDto;
    }

    public MeetingsEntityDto createMeetingService(Map<String, String> meetingData){

        System.out.println(meetingData);
        System.out.println(meetingData.keySet());
        System.out.println(meetingData.entrySet());
        System.out.println(meetingData.get("title"));
        System.out.println(meetingData.get("date"));
        System.out.println(meetingData.get("time"));
        System.out.println(meetingData.get("duration"));

        String title = meetingData.get("title");
        String description = meetingData.get("description");
        long room_id = Long.parseLong(meetingData.get("venue"));

        LocalDate lDate = LocalDate.parse(meetingData.get("date"));
        LocalTime lTime = LocalTime.parse(meetingData.get("time"));
        LocalTime lDuration = LocalTime.parse(meetingData.get("duration"));

        Optional<RoomsEntity> roomsEntity = roomsRepositoryJPA.findById(room_id);

        MeetingsEntity meetingsEntity = new MeetingsEntity(title, lDate, lTime, lDuration, roomsEntity.get(), description);

        MeetingsEntityDto meetingsEntityDto = new MeetingsEntityDtoConverter().meetingsEntityToMeetingsEntityDtoMapper(meetingsRepositoryJPA.save(meetingsEntity));

        return meetingsEntityDto;
    }

    public MeetingsEntityDto deleteMeeting(Long id) {
        Optional<MeetingsEntity> roomsEntity = meetingsRepositoryJPA.findById(id);
        if(!roomsEntity.isPresent()){
            return new MeetingsEntityDto();
        }
        meetingsRepositoryJPA.deleteById(id);
        return new MeetingsEntityDtoConverter().meetingsEntityToMeetingsEntityDtoMapper(roomsEntity.get());
    }
}
