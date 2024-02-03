package com.example.backend.film;

import java.util.List;

public interface FilmService {
  List<Film> getAll();

  Film getById(Integer id);

  Film findByTitle(String title);

  Film save(Film film);
}