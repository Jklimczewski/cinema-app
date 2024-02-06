package com.example.backend.film;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

  private final FilmRepository filmRepository;

  public FilmServiceImpl(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  @Override
  public Film save(Film film) {
    return filmRepository.save(film);
  }

  @Override
  public Film findByTitle(String title) {
    return filmRepository.findByTitle(title);
  }

  @Override
  public Film getById(Integer id) {
    return filmRepository.findById(id).get();
  }

  @Override
  public List<Film> getAll() {
    return filmRepository.findAll();
  }

  @Override
  public Film update(Integer id, Film film) {
    Film filmToUpdate = getById(id);
    filmToUpdate.update(film);
    return filmRepository.save(filmToUpdate);
  }

  @Override
  public void deleteById(Integer id) {
    Film filmToDelete = getById(id);
    filmRepository.delete(filmToDelete);
  }
}
