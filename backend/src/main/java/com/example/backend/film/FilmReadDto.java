package com.example.backend.film;

import java.util.List;

import com.example.backend.actor.ActorReadDto;
import com.example.backend.schedule.ScheduleReadDto;
import lombok.Builder;

@Builder
public record FilmReadDto(
    Integer id,
    String title,
    String picture,
    String genre,
    Integer age,
    String director,
    List<ScheduleReadDto> schedules,
    List<ActorReadDto> actors) {
}
