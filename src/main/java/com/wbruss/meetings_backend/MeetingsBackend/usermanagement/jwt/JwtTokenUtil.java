package com.wbruss.meetings_backend.MeetingsBackend.usermanagement.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 55L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 3600;

    private String secret = "springbesecurespringbesecurespringbesecurespringbesecurespringbesecurespringbesecurespringbesecurespringbesecure";

    public String getUserEmailFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        return getUserEmailFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    private Boolean isTokenExpired(String token){
        return getExpirationDateFromToken(token).before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        final JwtParser build = Jwts.parserBuilder().setSigningKey(secret.getBytes()).build();
        Claims claims = build.parseClaimsJws(token).getBody();
        System.out.println("Claims" + claims.toString());
        return claims;
    }
}
