package com.example.backend.film;

import java.util.List;

public interface FilmService {
  List<Film> getAll();

  Film getById(Integer id);

  Film findByTitle(String title);

  Film save(Film film);

  Film update(Integer id, Film film);

  void deleteById(Integer id);
}