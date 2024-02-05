package com.example.backend.film;

import java.util.List;

import lombok.Builder;

@Builder
public record FilmWriteDto(
    String title,
    String picture,
    String genre,
    String director,
    Integer age,
    List<Integer> actorsIds) {
}
