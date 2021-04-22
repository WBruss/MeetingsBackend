package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.security;

import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser.AppUser;
import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser.AppUserRepository;
import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AppUserAuthenticationManager implements AuthenticationManager {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(authentication.getName());

        if(optionalAppUser.isPresent()){
            if(passwordEncoder.matches(authentication.getCredentials().toString(), optionalAppUser.get().getPassword())){
                List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
                // TODO: Sort out user roles
                return new UsernamePasswordAuthenticationToken(
                        authentication.getPrincipal(),
                        authentication.getCredentials(),
                        grantedAuthorityList
                );
            }else {
                throw new BadCredentialsException("Wrong Password");
            }
        }else {
            System.out.println("Sign Up user");
            registrationService.register(authentication);
            throw new BadCredentialsException("Wrong Email");
        }
    }
}
