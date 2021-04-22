package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {

    private String userEmail;
    private String userPassword;
}
