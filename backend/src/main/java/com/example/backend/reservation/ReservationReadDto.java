package com.example.backend.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record ReservationReadDto(
    UUID id,
    LocalDate showDate,
    String showTime,
    String filmTitle,
    String filmPicture,
    List<Integer> pickedSeats,
    String username) {
}
