package com.example.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.backend.config.SecurityUtil;
import com.example.backend.film.Film;
import com.example.backend.film.FilmService;
import com.example.backend.reservation.Reservation;
import com.example.backend.reservation.ReservationService;
import com.example.backend.reservation.ReservationWriteDto;
import com.example.backend.schedule.Schedule;
import com.example.backend.schedule.ScheduleMapper;
import com.example.backend.schedule.ScheduleService;
import com.example.backend.user.UserEntity;
import com.example.backend.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CinemaController {

  private final ScheduleService scheduleService;
  private final FilmService filmService;
  private final ReservationService reservationService;
  private final UserService userService;

  public CinemaController(ScheduleService scheduleService, FilmService filmService,
      ReservationService reservationService, UserService userService, ScheduleMapper scheduleMapper) {
    this.scheduleService = scheduleService;
    this.filmService = filmService;
    this.reservationService = reservationService;
    this.userService = userService;
  }

  @GetMapping("/")
  public String welcomeMessage() {
    return "index";
  }

  @GetMapping("/repertoire")
  public String repertoire(@RequestParam(name = "date", defaultValue = "2024-02-06") String stringDate, Model model) {
    LocalDate date = LocalDate.parse(stringDate);
    Map<Film, List<Schedule>> groupedSchedules = scheduleService.getAllGroupedByFilmAndDate(date);

    model.addAttribute("repertoire", groupedSchedules);
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

  @PostMapping("/reservation")
  public String makeReservation(@RequestBody ReservationWriteDto writeDto, Model model) {
    Schedule scheduleExists = scheduleService.getById(writeDto.scheduleId());
    if (scheduleExists != null) {
      reservationService.save(writeDto);
      return "redirect:/cart";
    }
    return "redirect:/error";
  }

  @GetMapping("/cart")
  public String showCart(Model model) {
    String username = SecurityUtil.getSessionUser();
    if (username != null) {
      UserEntity user = userService.findByEmail(username);
      List<Reservation> reservations = reservationService.findAllByUser(user);
      if (reservations.size() > 0) {
        model.addAttribute("reservations", reservations);
        return "cart";
      }
      return "cartNotFound";
    }
    return "redirect:/login";
  }

  @GetMapping("/cart/payment")
  public String payment(Model model) {
    String username = SecurityUtil.getSessionUser();
    if (username != null) {
      UserEntity user = userService.findByEmail(username);
      List<Reservation> reservations = reservationService.findAllByUser(user);
      if (reservations.size() > 0) {
        for (Reservation reservation : reservations) {
          scheduleService.updateAvailableSeats(reservation.getSchedule().getId(), reservation.getPickedSeats());
          reservationService.finalize(reservation);
        }
      }
      return "redirect:/cart";
    }
    return "redirect:/login";
  }

  @GetMapping("/reservation/{reservationId}")
  public String showReservation(@PathVariable UUID reservationId, Model model) {
    Reservation reservation = reservationService.findById(reservationId);
    String username = SecurityUtil.getSessionUser();
    if (username != null) {
      UserEntity user = userService.findByEmail(username);
      if (reservation.getMadeBy().getId() == user.getId()) {
        model.addAttribute("reservation", reservation);
        return "reservation";
      }
      return "cart";
    }
    return "redirect:/login";
  }

  @PostMapping("/reservation/{reservationId}/update")
  public String updateReservation(@PathVariable UUID reservationId, @RequestBody ReservationWriteDto writeDto,
      Model model) {
    Reservation reservation = reservationService.findById(reservationId);
    String username = SecurityUtil.getSessionUser();
    if (username != null) {
      UserEntity user = userService.findByEmail(username);
      if (reservation.getMadeBy().getId() == user.getId()) {
        reservationService.updatePickedSeats(reservationId, writeDto.pickedSeats());
      }
      return "redirect:/cart";
    }
    return "redirect:/login";
  }

  @GetMapping("/reservation/{reservationId}/delete")
  public String deleteReservation(@PathVariable UUID reservationId, Model model) {
    Reservation reservation = reservationService.findById(reservationId);
    String username = SecurityUtil.getSessionUser();
    if (username != null) {
      UserEntity user = userService.findByEmail(username);
      if (reservation.getMadeBy().getId() == user.getId()) {
        reservationService.deleteById(reservation.getId());
      }
      return "redirect:/cart";
    }
    return "redirect:/login";
  }

}
