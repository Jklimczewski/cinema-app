package com.example.backend.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {

  Schedule getById(UUID id);

  List<Schedule> getAllByDate(LocalDate date);

  Schedule save(Schedule schedule);

  List<Schedule> findAll();

  void updateAvailableSeats(UUID id, List<Integer> pickedSeats);
}
