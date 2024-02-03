package com.example.backend.reservation;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservationMapper {

  @Mapping(target = "id", ignore = true)
  Reservation fromWriteDto(ReservationWriteDto writeDto);

  // @Mapping(target = "showDate", expression =
  // "java(reservation.getSchedule().getShowDate())")
  // @Mapping(target = "showTime", expression =
  // "java(reservation.getSchedule.getShowTime())")
  // @Mapping(target = "filmTitle", expression =
  // "java(reservation.getSchedule().getFilm().getTitle())")
  // @Mapping(target = "filmPicture", expression =
  // "java(reservation.getSchedule.getFilm().getPicture())")
  ReservationReadDto toReadDto(Reservation reservation);
}
