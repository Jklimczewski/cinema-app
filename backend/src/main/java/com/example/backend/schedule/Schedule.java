package com.example.backend.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.backend.film.Film;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "film_id", nullable = false)
  private Film film;

  @Column(nullable = false)
  private LocalDate showDate;

  @Column(nullable = false)
  private String showTime;

  @Column(nullable = false)
  private List<Integer> availableSeats;
}
