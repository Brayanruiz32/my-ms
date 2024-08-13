package com.marreros.auth_server.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marreros.auth_server.dto.TokenDTO;
import com.marreros.auth_server.dto.UserDTO;
import com.marreros.auth_server.entities.UserEntity;
import com.marreros.auth_server.helpers.JwtHelper;
import com.marreros.auth_server.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtHelper jwtHelper;
    @Override
    public TokenDTO login(UserDTO user) {
        final var userFromDB = this.userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new EntityNotFoundException());
        this.validPassword(user, userFromDB);
        return TokenDTO.builder()
                .accessToken(jwtHelper.createToken(userFromDB.getUsername()))
                .build();
    }

    @Override
    public TokenDTO validateToken(TokenDTO token) {
        if (this.jwtHelper.validateToken(token.getAccessToken())) {
            return TokenDTO.builder().accessToken(token.getAccessToken()).build();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Error en la validacion");
    }



    private void validPassword(UserDTO userDTO, UserEntity userEntity){
        if (!passwordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "error en la contraseña");
        }
    }



}
