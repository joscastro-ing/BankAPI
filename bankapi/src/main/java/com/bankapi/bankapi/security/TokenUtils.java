package com.bankapi.bankapi.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
    private final static String ACCES_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdbz9URb2rf8e7Ud";
    private final static Long ACCES_TOKEN_VALIDITY_SECONDS = 30*60L;

    public static String createToken(String nombre, String email, Collection<? extends GrantedAuthority> authorities){
        long expirationTime = ACCES_TOKEN_VALIDITY_SECONDS*1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Collection<? extends GrantedAuthority>> extra = new HashMap<>();
        extra.put("name", authorities);

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("auth", authorities.stream()
                .map(s -> new SimpleGrantedAuthority(s.getAuthority()))
                .filter(Objects::nonNull)
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
        .setClaims(claims)
        .setExpiration(expirationDate)
        .signWith(Keys.hmacShaKeyFor(ACCES_TOKEN_SECRET.getBytes()))
        .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
            .setSigningKey(ACCES_TOKEN_SECRET.getBytes())
            .build()
            .parseClaimsJws(token)
            .getBody();

            String email = claims.getSubject();
            List<String> authList =  ((List<String>) claims.get("auth"));
            
            List<GrantedAuthority> authorities = authList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(email, null, authorities);

        }catch(JwtException e){
            return null;
        }
    }

}
