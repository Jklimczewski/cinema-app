package com.example.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.backend.config.SecurityUtil;
import com.example.backend.film.Film;
import com.example.backend.film.FilmService;
import com.example.backend.reservation.Reservation;
import com.example.backend.reservation.ReservationService;
import com.example.backend.reservation.ReservationWriteDto;
import com.example.backend.schedule.Schedule;
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
      ReservationService reservationService, UserService userService) {
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

  @PostMapping("/reservation")
  public String makeReservation(@RequestBody ReservationWriteDto writeDto, Model model) {
    Schedule scheduleExists = scheduleService.getById(writeDto.scheduleId());
    if (scheduleExists != null) {
      reservationService.save(writeDto);
      return "redirect:/cart";
    }
    return "redirect:/error";
  }

  @GetMapping("/reservation/{reservationId}/delete")
  public String deleteReservation(@PathVariable UUID reservationId, Model model) {
    Reservation reservation = reservationService.findById(reservationId);
    String username = SecurityUtil.getSessionUser();
    if (username != null) {
      UserEntity user = userService.findByEmail(username);
      if (reservation.getMadeBy().getId() == user.getId()) {
        reservationService.delete(reservation);
        return "redirect:/cart";
      }
    }
    return "redirect:/login";
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
      return "noItemsInCart";
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
        return "redirect:/";
      }
      return "redirect:/cart";
    }
    return "redirect:/login";
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

}
