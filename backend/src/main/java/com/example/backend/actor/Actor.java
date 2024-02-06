package com.example.backend.actor;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.List;

import com.example.backend.film.Film;
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

  @ManyToMany(mappedBy = "actors", fetch = FetchType.EAGER)
  private List<Film> filmsPlayed;

  public void update(Actor actor) {
    if (actor.name != null) {
      name = actor.name;
    }

    if (actor.surrname != null) {
      surrname = actor.surrname;
    }
  }
}
