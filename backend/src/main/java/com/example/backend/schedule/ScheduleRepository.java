package com.example.backend.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

  List<Schedule> findByShowDate(LocalDate showDate);

  @Query("SELECT s FROM Schedule s JOIN FETCH s.film WHERE s.showDate = :date ORDER BY s.film, s.showTime")
  List<Schedule> findByShowDateOrderByFilmAndShowTime(@Param("date") LocalDate date);
}
