package com.example.backend.user;

public interface UserService {

  UserEntity save(RegistrationWriteDto writeDto);

  UserEntity findByEmail(String email);

}