package com.example.backend.reservation;

import java.util.List;

public interface ReservationService {

  List<Reservation> findAll();

  Reservation save(Reservation reservation);

}
