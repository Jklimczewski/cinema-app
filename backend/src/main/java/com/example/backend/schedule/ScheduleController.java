package com.example.backend.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import com.example.backend.film.Film;
import com.example.backend.film.FilmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
  private final ScheduleService scheduleService;
  private final FilmService filmService;
  private final ScheduleMapper scheduleMapper;

  public ScheduleController(ScheduleService scheduleService, FilmService filmService, ScheduleMapper scheduleMapper) {
    this.scheduleService = scheduleService;
    this.filmService = filmService;
    this.scheduleMapper = scheduleMapper;
  }

  @GetMapping
  public List<ScheduleReadDto> findAll() {
    return scheduleService.findAll()
        .stream()
        .map(scheduleMapper::toReadDto)
        .toList();
  }

  @GetMapping("/{scheduleId}")
  public ScheduleReadDto getScheduleById(@PathVariable UUID scheduleId) {
    return scheduleMapper.toReadDto(scheduleService.getById(scheduleId));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ScheduleReadDto create(@RequestBody ScheduleWriteDto writeDto) {
    Film film = filmService
        .getById(writeDto.filmId());

    List<Integer> seats = IntStream.rangeClosed(1, 10).boxed().toList();

    Schedule scheduleToCreate = Schedule.builder()
        .film(film)
        .showDate(writeDto.showDate())
        .showTime(writeDto.showTime())
        .availableSeats(seats)
        .build();

    return scheduleMapper.toReadDto(
        scheduleService.save(scheduleToCreate));
  }

  @GetMapping("/filter")
  public List<ScheduleReadDto> getAllSchedulesByDate(@RequestParam(name = "date") String stringDate) {
    LocalDate date = LocalDate.parse(stringDate);
    return scheduleService.getAllByDate(date)
        .stream()
        .map(scheduleMapper::toReadDto)
        .toList();
  }
}
