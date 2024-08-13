package com.marreros.auth_server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marreros.auth_server.dto.TokenDTO;
import com.marreros.auth_server.dto.UserDTO;
import com.marreros.auth_server.services.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> jwtCreated(@RequestBody UserDTO user){
        return ResponseEntity.ok(this.authService.login(user));
    }

    @PostMapping("/jwt")
    public ResponseEntity<TokenDTO> jwtValidate(@RequestHeader String accessToken){
        return ResponseEntity.ok(this.authService.validateToken(TokenDTO.builder().accessToken(accessToken).build()));
    } 
}
