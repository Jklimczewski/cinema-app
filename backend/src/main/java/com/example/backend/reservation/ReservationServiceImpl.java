package com.example.backend.reservation;

import java.util.List;
import java.util.UUID;

import com.example.backend.config.SecurityUtil;
import com.example.backend.schedule.Schedule;
import com.example.backend.schedule.ScheduleRepository;
import com.example.backend.user.UserEntity;
import com.example.backend.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepository reservationRepository;
  private final UserRepository userRepository;
  private final ScheduleRepository scheduleRepository;

  public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository,
      ScheduleRepository scheduleRepository) {
    this.reservationRepository = reservationRepository;
    this.userRepository = userRepository;
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public Reservation save(ReservationWriteDto writeDto) {
    Schedule schedule = scheduleRepository.findById(writeDto.scheduleId()).get();
    String username = SecurityUtil.getSessionUser();
    UserEntity user = userRepository.findByEmail(username);
    Reservation reservationToCreate = Reservation.builder()
        .schedule(schedule)
        .madeBy(user)
        .pickedSeats(writeDto.pickedSeats())
        .build();
    return reservationRepository.save(reservationToCreate);
  }

  @Override
  public List<Reservation> findAllByUser(UserEntity user) {
    return reservationRepository.findByMadeByAndFinalized(user, false);
  }

  @Override
  public Reservation findById(UUID reservationId) {
    return reservationRepository.findById(reservationId).get();
  }

  @Override
  public void delete(Reservation reservation) {
    reservationRepository.deleteById(reservation.getId());
  }

  @Override
  public void finalize(Reservation reservation) {
    reservation.setFinalized(true);
    reservationRepository.save(reservation);
  }

}
