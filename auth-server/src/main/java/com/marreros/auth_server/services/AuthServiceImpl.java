package com.marreros.auth_server.services;

import javax.management.RuntimeErrorException;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marreros.auth_server.dto.TokenDTO;
import com.marreros.auth_server.dto.UserDTO;
import com.marreros.auth_server.entities.UserEntity;
import com.marreros.auth_server.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDTO login(UserDTO user) {
        return null;
    }

    @Override
    public TokenDTO validateToken(TokenDTO token) {
        return null;
    }

    @Bean
    public PasswordEncoder passwordEncoderBean(){
        return new BCryptPasswordEncoder();
    }

    private void validPassword(UserDTO userDTO, UserEntity userEntity){
        if (!passwordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "error en la contraseña");
        }
    }



}
