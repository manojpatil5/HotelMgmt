package com.hotelmanagement.hotel.repository;

import com.hotelmanagement.hotel.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, Short> {
    Optional<ReservationStatus> findByName(String name);
}

