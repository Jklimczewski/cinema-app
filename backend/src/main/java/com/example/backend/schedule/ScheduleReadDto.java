package com.example.backend.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record ScheduleReadDto(
                UUID id,
                LocalDate showDate,
                String showTime,
                String filmTitle,
                String filmPicture,
                List<Integer> availableSeats) {
}
