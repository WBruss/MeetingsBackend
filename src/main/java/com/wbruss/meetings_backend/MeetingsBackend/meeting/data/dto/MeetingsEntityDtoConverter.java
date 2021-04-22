package com.wbruss.meetings_backend.MeetingsBackend.meeting.data.dto;

import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity.MeetingsEntity;
import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.repository.jpa.MeetingsRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;

public class MeetingsEntityDtoConverter {

    @Autowired
    private MeetingsRepositoryJPA meetingsRepositoryJPA;

    public MeetingsEntityDto meetingsEntityToMeetingsEntityDtoMapper(MeetingsEntity meetingsEntity){
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
}
