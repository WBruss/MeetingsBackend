package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.registration;

import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser.AppUser;
import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser.AppUserRole;
import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public String register(Authentication request) {

        appUserService.signUpUser(
                new AppUser(
                      request.getPrincipal().toString(),
                      request.getCredentials().toString(),
                      AppUserRole.EMPLOYEE
                )
        );

        return "logged in";
    }
}
