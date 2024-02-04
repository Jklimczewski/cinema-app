package com.example.backend.film;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Set;

import com.example.backend.actor.Actor;
import com.example.backend.schedule.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

  @JsonIgnore
  @OneToMany(mappedBy = "film")
  private Set<Schedule> schedules;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private Set<Actor> actors;

  public void addActor(Actor actor) {
    actors.add(actor);
  }

}
