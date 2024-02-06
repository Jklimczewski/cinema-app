package com.example.backend.reservation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservationMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "schedule", ignore = true)
  @Mapping(target = "madeBy", ignore = true)
  @Mapping(target = "finalized", ignore = true)
  Reservation fromWriteDto(ReservationWriteDto writeDto);

  @Mapping(target = "showDate", expression = "java(reservation.getSchedule().getShowDate())")
  @Mapping(target = "showTime", expression = "java(reservation.getSchedule().getShowTime())")
  @Mapping(target = "filmTitle", expression = "java(reservation.getSchedule().getFilm().getTitle())")
  @Mapping(target = "filmPicture", expression = "java(reservation.getSchedule().getFilm().getPicture())")
  @Mapping(target = "username", expression = "java(reservation.getMadeBy().getUsername())")
  ReservationReadDto toReadDto(Reservation reservation);
}
