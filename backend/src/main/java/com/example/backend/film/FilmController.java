package com.example.backend.film;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.backend.actor.Actor;
import com.example.backend.actor.ActorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/films")
public class FilmController {
  private final FilmService filmService;
  private final ActorService actorService;
  private final FilmMapper filmMapper;

  public FilmController(FilmService filmService, ActorService actorService, FilmMapper filmMapper) {
    this.filmService = filmService;
    this.actorService = actorService;
    this.filmMapper = filmMapper;
  }

  @GetMapping("")
  public List<FilmReadDto> getAllFilms() {
    return filmService.getAll()
        .stream()
        .map(filmMapper::toReadDto)
        .toList();
  }

  @GetMapping("/{filmId}")
  public FilmReadDto getFilmById(@PathVariable Integer filmId) {
    return filmMapper.toReadDto(filmService.getById(filmId));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public FilmReadDto create(@RequestBody FilmWriteDto writeDto) {
    Set<Actor> actors = writeDto.actorsIds().stream()
        .map(actorService::getById)
        .collect(Collectors.toSet());

    Film filmToCreate = Film.builder()
        .title(writeDto.title())
        .picture(writeDto.picture())
        .genre(writeDto.genre())
        .age(writeDto.age())
        .director(writeDto.director())
        .actors(actors)
        .build();

    return filmMapper.toReadDto(
        filmService.save(filmToCreate));
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{filmId}/actors/{actorId}")
  public FilmReadDto addActorToFilm(@PathVariable Integer filmId, @PathVariable Integer actorId) {
    Film film = filmService.getById(filmId);
    Actor actor = actorService.getById(actorId);
    film.addActor(actor);
    return filmMapper.toReadDto(filmService.save(film));
  }
}
