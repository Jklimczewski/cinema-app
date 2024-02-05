package com.example.backend.schedule;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.backend.film.Film;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  private final ScheduleRepository scheduleRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public List<Schedule> getAllByDate(LocalDate date) {
    return scheduleRepository.findByShowDate(date);
  }

  @Override
  public Schedule save(Schedule schedule) {
    return scheduleRepository.save(schedule);
  }

  @Override
  public List<Schedule> findAll() {
    return scheduleRepository.findAll();
  }

  @Override
  public Schedule getById(UUID id) {
    return scheduleRepository.findById(id).get();
  }

  @Override
  public Schedule updateAvailableSeats(UUID scheduleId, List<Integer> pickedSeats) {
    Schedule schedule = scheduleRepository.findById(scheduleId).get();

    List<Integer> availableSeats = schedule.getAvailableSeats();

    for (Integer pickedSeat : pickedSeats) {
      if (availableSeats.contains(pickedSeat)) {
        availableSeats.remove(pickedSeat);
      } else {
        System.err.println("No available seat nr:" + pickedSeat.toString());
      }
    }
    return scheduleRepository.save(schedule);
  }

  @Override
  public Map<Film, List<Schedule>> getAllGroupedByFilmAndDate(LocalDate date) {
    List<Schedule> schedules = scheduleRepository.findByShowDate(date);

    Map<Film, List<Schedule>> groupedByFilm = schedules.stream()
        .collect(Collectors.groupingBy(Schedule::getFilm));

    groupedByFilm.forEach((film, filmSchedules) -> filmSchedules.sort(Comparator.comparing(Schedule::getShowTime)));

    return groupedByFilm;
  }
}
