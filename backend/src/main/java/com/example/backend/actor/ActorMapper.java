package com.example.backend.actor;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ActorMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "filmsPlayed", ignore = true)
  Actor fromWriteDto(ActorWriteDto writeDto);

  ActorReadDto toReadDto(Actor actor);
}
