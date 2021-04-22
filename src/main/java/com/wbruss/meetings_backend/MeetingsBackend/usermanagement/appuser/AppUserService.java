package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public String signUpUser(AppUser appUser) {

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
        return "signUpUser: " + appUser.toString();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);

        if(optionalAppUser.isPresent()){
            AppUser appUser = optionalAppUser.get();

        }else {
//            throw new UsernameNotFoundException("The user does not exist");
            // TODO: Set user for approval
            System.out.println("Email" + email);
        }

        return optionalAppUser.get();
    }
}
