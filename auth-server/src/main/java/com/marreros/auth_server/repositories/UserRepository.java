package com.marreros.auth_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marreros.auth_server.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
