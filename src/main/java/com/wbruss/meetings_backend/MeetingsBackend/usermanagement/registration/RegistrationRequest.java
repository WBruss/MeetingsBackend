package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.registration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private String email;
    private String role;
}
