package com.marreros.auth_server.helpers;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtHelper {

    @Value("${application.jwt-secret}")
    private String jwtSecret;

    public String createToken(String username){
        final var now = new Date();
        final var expirationDate = new Date(now.getTime() + (3600*1000));
        return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(expirationDate)
            .signWith(getSecretKey())
            .compact();
    }
    

    public boolean validateToken(String token){
        final var expirationDate = this.getExpirationDate(token);
        return expirationDate.after(new Date());
    }


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
        log.info("contraseña "+jwtSecret);
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
