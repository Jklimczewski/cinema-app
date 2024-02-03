package com.example.backend.film;

import java.util.Set;

import lombok.Builder;

@Builder
public record FilmWriteDto(
    String title,
    String picture,
    String genre,
    String director,
    Integer age,
    Set<Integer> actorsIds) {
}
