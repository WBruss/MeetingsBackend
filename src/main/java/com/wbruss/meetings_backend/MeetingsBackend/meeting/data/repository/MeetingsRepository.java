package com.wbruss.meetings_backend.MeetingsBackend.meeting.data.repository;

import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.dto.MeetingsEntityDto;

import java.text.ParseException;
import java.util.List;

public interface MeetingsRepository {
    List<MeetingsEntityDto> getMeetings() throws ParseException;
}
