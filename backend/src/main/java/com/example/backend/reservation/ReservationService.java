package com.example.backend.reservation;

import java.util.List;
import java.util.UUID;

import com.example.backend.user.UserEntity;

public interface ReservationService {

  Reservation findById(UUID reservationId);

  List<Reservation> findAllByUser(UserEntity user);

  Reservation save(ReservationWriteDto writeDto);

  void finalize(Reservation reservation);

  void delete(Reservation reservation);

}
