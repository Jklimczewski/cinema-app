package com.example.backend.film;

import java.util.Set;

import com.example.backend.actor.ActorReadDto;
import lombok.Builder;

@Builder
public record FilmReadDto(
    Integer id,
    String title,
    String picture,
    String genre,
    Integer age,
    String director,
    Set<ActorReadDto> actors) {
}
