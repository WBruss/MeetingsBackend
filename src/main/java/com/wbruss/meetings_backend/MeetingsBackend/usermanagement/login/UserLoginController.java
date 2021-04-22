package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.login;

import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser.AppUserService;
import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.jwt.JwtTokenUtil;
import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.jwt.UsernameAndPasswordAuthenticationRequest;
import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.security.AppUserAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class UserLoginController {

    @Autowired
    private AppUserAuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/")
    public ResponseEntity<?> userLogin(@RequestBody UsernameAndPasswordAuthenticationRequest loginRequest){

        try {
            authenticate(loginRequest.getUserEmail(), loginRequest.getUserPassword());
            final UserDetails userDetails = appUserService.loadUserByUsername(loginRequest.getUserEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private void authenticate(String userEmail, String userPassword) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
        }catch (Exception e){
            throw new Exception("Wrong username and/or password");
        }
    }
}
