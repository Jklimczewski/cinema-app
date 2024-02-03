package com.example.backend.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

  List<Schedule> findByShowDate(LocalDate showDate);
}
