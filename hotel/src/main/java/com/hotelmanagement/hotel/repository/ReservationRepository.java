package com.hotelmanagement.hotel.repository;

import com.hotelmanagement.hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        select count(r) > 0
        from Reservation r
        where r.room.id = :roomId
          and r.status.name in ('BOOKED','CHECKED_IN')
          and :checkIn < r.checkOutDate
          and :checkOut > r.checkInDate
        """)
    boolean hasOverlap(Integer roomId, LocalDate checkIn, LocalDate checkOut);
}

