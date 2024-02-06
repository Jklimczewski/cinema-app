package com.example.backend.reservation;

import java.util.List;
import java.util.UUID;

import com.example.backend.user.UserEntity;

public interface ReservationService {

  Reservation findById(UUID reservationId);

  List<Reservation> findAllByUser(UserEntity user);

  Reservation save(ReservationWriteDto writeDto);

  Reservation finalize(Reservation reservation);

  Reservation updatePickedSeats(UUID reservationId, List<Integer> pickedSeats);

  void deleteById(UUID id);

}
