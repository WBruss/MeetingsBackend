package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.registration;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/")
    public String register(@RequestBody RegistrationRequest request){
//        String register = registrationService.register(request);
        return "register";
    }
}
