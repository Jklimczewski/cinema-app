package com.example.backend.schedule;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ScheduleWriteDto(
                LocalDate showDate,
                Integer filmId,
                String showTime) {
}
