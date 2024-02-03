package com.example.backend.schedule;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ScheduleMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "film", ignore = true)
  @Mapping(target = "availableSeats", ignore = true)
  Schedule fromWriteDto(ScheduleWriteDto writeDto);

  @Mapping(target = "filmTitle", expression = "java(schedule.getFilm().getTitle())")
  @Mapping(target = "filmPicture", expression = "java(schedule.getFilm().getPicture())")
  ScheduleReadDto toReadDto(Schedule schedule);
}
