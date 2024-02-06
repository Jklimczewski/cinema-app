package com.example.backend.film;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.List;

import com.example.backend.actor.Actor;
import com.example.backend.schedule.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String picture;

  @Column(nullable = false, unique = true)
  private String title;

  @Column(nullable = false)
  private String genre;

  @Column(nullable = false)
  private Integer age;

  @Column(nullable = false)
  private String director;

  @OneToMany(mappedBy = "film")
  private List<Schedule> schedules;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private List<Actor> actors;

  public void addActor(Actor actor) {
    actors.add(actor);
  }

  public void update(Film film) {
    if (film.picture != null) {
      picture = film.picture;
    }

    if (film.genre != null) {
      genre = film.genre;
    }

    if (film.age != null) {
      age = film.age;
    }

    if (film.director != null) {
      director = film.director;
    }
  }
}
