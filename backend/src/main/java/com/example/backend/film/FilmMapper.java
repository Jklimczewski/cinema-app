package com.example.backend.film;

import com.example.backend.actor.ActorMapper;
import com.example.backend.schedule.ScheduleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {
    ActorMapper.class,
    ScheduleMapper.class
})
public interface FilmMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "actors", ignore = true)
  Film fromWriteDto(FilmWriteDto writeDto);

  FilmReadDto toReadDto(Film film);
}
