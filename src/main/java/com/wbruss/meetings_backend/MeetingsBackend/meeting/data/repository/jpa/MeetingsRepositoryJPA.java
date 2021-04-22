package com.wbruss.meetings_backend.MeetingsBackend.meeting.data.repository.jpa;

import com.wbruss.meetings_backend.MeetingsBackend.meeting.data.entity.MeetingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingsRepositoryJPA extends JpaRepository<MeetingsEntity, Long> {
}
