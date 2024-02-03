package com.example.backend.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
}
