package com.example.backend.actor;

import org.mapstruct.Mapper;

@Mapper
public interface ActorMapper {

  Actor fromWriteDto(ActorWriteDto writeDto);

  ActorReadDto toReadDto(Actor actor);
}
