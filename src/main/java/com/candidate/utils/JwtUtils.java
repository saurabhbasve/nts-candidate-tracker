package com.candidate.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 86400000; // 1 day

    public String generateToken(String email, Set<GrantedAuthority> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, Base64.getDecoder().decode(secretKey))
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(secretKey))
                .parseClaimsJws(token)
                .getBody();
    }
}
