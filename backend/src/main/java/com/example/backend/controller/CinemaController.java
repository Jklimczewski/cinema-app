package com.example.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.backend.film.Film;
import com.example.backend.film.FilmService;
import com.example.backend.schedule.Schedule;
import com.example.backend.schedule.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CinemaController {

  private final ScheduleService scheduleService;
  private final FilmService filmService;

  public CinemaController(ScheduleService scheduleService, FilmService filmService) {
    this.scheduleService = scheduleService;
    this.filmService = filmService;
  }

  @GetMapping("/")
  public String welcomeMessage() {
    return "index";
  }

  @GetMapping("/repertoire")
  public String repertoire(@RequestParam(name = "date", defaultValue = "2024-01-28") String stringDate, Model model) {
    LocalDate date = LocalDate.parse(stringDate);
    List<Schedule> repertoireByDate = scheduleService.getAllByDate(date);
    model.addAttribute("repertoire", repertoireByDate);
    return "repertoire";
  }

  @GetMapping("/repertoire/{scheduleId}")
  public String seats(@PathVariable UUID scheduleId, Model model) {
    Schedule pickedSchedule = scheduleService.getById(scheduleId);
    model.addAttribute("schedule", pickedSchedule);
    return "seats";
  }

  @GetMapping("/films/{filmTitle}")
  public String filmDetails(@PathVariable String filmTitle, Model model) {
    String convertedTitle = filmTitle.replace('-', ' ').toLowerCase();
    Film film = filmService.findByTitle(convertedTitle);
    if (film != null) {
      model.addAttribute("film", film);
      return "filmDetails";
    } else {
      return "filmNotFound";
    }
  }

  @GetMapping("/payment")
  public String payment() {
    return "payment";
  }

}
