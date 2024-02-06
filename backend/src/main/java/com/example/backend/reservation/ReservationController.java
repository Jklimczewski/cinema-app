package com.example.backend.reservation;

import java.util.List;
import java.util.UUID;

import com.example.backend.config.SecurityUtil;
import com.example.backend.schedule.ScheduleService;
import com.example.backend.user.UserEntity;
import com.example.backend.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
  private final ReservationService reservationService;
  private final ScheduleService scheduleService;
  private final ReservationMapper reservationMapper;
  private final UserService userService;

  public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper,
      ScheduleService scheduleService, UserService userService) {
    this.reservationService = reservationService;
    this.reservationMapper = reservationMapper;
    this.scheduleService = scheduleService;
    this.userService = userService;
  }

  @GetMapping
  public List<ReservationReadDto> findAllByUser() {
    String username = SecurityUtil.getSessionUser();
    UserEntity user = userService.findByEmail(username);
    return reservationService.findAllByUser(user)
        .stream()
        .map(reservationMapper::toReadDto)
        .toList();
  }

  @GetMapping("/{id}")
  public ReservationReadDto getReservationById(@PathVariable UUID id) {
    return reservationMapper.toReadDto(reservationService.findById(id));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ReservationReadDto create(@RequestBody ReservationWriteDto writeDto) {
    return reservationMapper.toReadDto(
        reservationService.save(writeDto));
  }

  @PutMapping("/{id}/update")
  public ReservationReadDto updateReservation(@PathVariable UUID id, @RequestBody ReservationWriteDto writeDto) {
    return reservationMapper.toReadDto(reservationService.updatePickedSeats(id, writeDto.pickedSeats()));
  }

  @PutMapping("/{id}/finalize")
  public ReservationReadDto updateReservationStatus(@PathVariable UUID id) {
    Reservation reservation = reservationService.findById(id);
    scheduleService.updateAvailableSeats(reservation.getSchedule().getId(), reservation.getPickedSeats());
    return reservationMapper.toReadDto(reservationService.finalize(reservation));
  }

  @GetMapping("/{id}/delete")
  public void deleteById(@PathVariable UUID id) {
    Reservation reservation = reservationService.findById(id);
    reservationService.deleteById(reservation.getId());
  }
}