package com.example.backend.actor;

import lombok.Builder;

@Builder
public record ActorReadDto(
    Integer id,
    String name,
    String surrname) {
}
