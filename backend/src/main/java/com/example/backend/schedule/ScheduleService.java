package com.example.backend.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.backend.film.Film;

public interface ScheduleService {

  Schedule getById(UUID id);

  List<Schedule> getAllByDate(LocalDate date);

  Schedule save(Schedule schedule);

  List<Schedule> findAll();

  Schedule updateAvailableSeats(UUID id, List<Integer> pickedSeats);

  Map<Film, List<Schedule>> getAllGroupedByFilmAndDate(LocalDate date);
}
