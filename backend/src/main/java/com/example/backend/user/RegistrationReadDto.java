package com.example.backend.user;

import lombok.Builder;

@Builder
public record RegistrationReadDto(
    Integer id,
    String username,
    String email,
    String password) {
}
