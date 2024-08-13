package com.marreros.auth_server.services;

import com.marreros.auth_server.dto.TokenDTO;
import com.marreros.auth_server.dto.UserDTO;

public interface AuthService {

    TokenDTO login(UserDTO user);

    TokenDTO validateToken(TokenDTO token);

}
