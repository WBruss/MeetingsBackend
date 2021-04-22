package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.jwt;

import com.wbruss.meetings_backend.MeetingsBackend.usermanagement.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AppUserService appUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String userEmail = null;

        final String headerToken = httpServletRequest.getHeader("Authorization");

        if(headerToken != null && headerToken.startsWith("Bearer ")){
            String token = headerToken.substring(7);
            System.out.println("JWT: " + token);
            userEmail = jwtTokenUtil.getUserEmailFromToken(token);
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = appUserService.loadUserByUsername(userEmail);
                if(jwtTokenUtil.validateToken(token, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }else {
                    // Invalid Token
                }
            }else {
                // No UserEmail in token
            }
        }else {
            // No Auth header present
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
