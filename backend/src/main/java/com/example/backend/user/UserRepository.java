package com.example.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  UserEntity findByEmail(String email);

  UserEntity findByUsername(String username);
}