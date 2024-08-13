package com.marreros.auth_server.helpers;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtHelper {

    private String jwtSecret;

    private Date getExpirationDate(String token){
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }


    private <T> T getClaimsFromToken(String token, Function<Claims, T> resolver){
        return resolver.apply(this.signToken(token));
    }

    private Claims signToken(String token){
        return Jwts
        .parserBuilder()
        .setSigningKey(getSecretKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
