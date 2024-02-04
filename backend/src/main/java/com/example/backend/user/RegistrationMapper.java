package com.example.backend.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegistrationMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "roles", ignore = true)
  UserEntity fromWriteDto(RegistrationWriteDto writeDto);

  RegistrationReadDto toReadDto(UserEntity userEntity);
}
