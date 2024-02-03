package com.example.backend.actor;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Set;

import com.example.backend.film.Film;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surrname;

  @JsonIgnore
  @ManyToMany(mappedBy = "actors", fetch = FetchType.EAGER)
  private Set<Film> filmsPlayed;
}
