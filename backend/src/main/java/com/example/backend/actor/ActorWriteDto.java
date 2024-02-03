package com.example.backend.actor;

import lombok.Builder;

@Builder
public record ActorWriteDto(
    String name,
    String surrname) {
}
