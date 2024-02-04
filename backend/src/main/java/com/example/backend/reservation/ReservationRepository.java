package com.example.backend.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;
import com.example.backend.user.UserEntity;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

  List<Reservation> findByMadeByAndFinalized(UserEntity madeBy, boolean finalized);
}
