package com.example.backend.reservation;

import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record ReservationWriteDto(
    UUID scheduleId,
    List<Integer> pickedSeats) {
}
