package com.hotelmanagement.hotel.service;

import com.hotelmanagement.hotel.dto.ReservationRequest;
import com.hotelmanagement.hotel.model.*;
import com.hotelmanagement.hotel.repository.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Map;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final CustomerRepository customerRepo;
    private final HotelRepository hotelRepo;
    private final RoomRepository roomRepo;
    private final ReservationStatusRepository statusRepo;

    // Optional: for stored-proc path
    private final SimpleJdbcCall createResProc;

    public ReservationService(ReservationRepository reservationRepo,
                              CustomerRepository customerRepo,
                              HotelRepository hotelRepo,
                              RoomRepository roomRepo,
                              ReservationStatusRepository statusRepo,
                              DataSource dataSource) {
        this.reservationRepo = reservationRepo;
        this.customerRepo = customerRepo;
        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
        this.statusRepo = statusRepo;

        // If you created hm.usp_CreateReservation in SQL Server, this enables calling it.
        this.createResProc = new SimpleJdbcCall(dataSource)
                .withSchemaName("hm")
                .withProcedureName("usp_CreateReservation");
    }

    /** Java-side booking with overlap check (no stored proc needed). */
    @Transactional
    public Reservation book(ReservationRequest req) {
        var customer = customerRepo.findById(req.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        var hotel = hotelRepo.findById(req.hotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        var room = roomRepo.findById(req.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if (!room.getHotel().getId().equals(hotel.getId())) {
            throw new IllegalArgumentException("Room does not belong to the specified hotel");
        }
        if (!req.checkOutDate().isAfter(req.checkInDate())) {
            throw new IllegalArgumentException("CheckOutDate must be after CheckInDate");
        }
        if (reservationRepo.hasOverlap(room.getId(), req.checkInDate(), req.checkOutDate())) {
            throw new IllegalStateException("Room is already reserved for these dates");
        }

        var booked = statusRepo.findByName("BOOKED")
                .orElseThrow(() -> new IllegalStateException("Status BOOKED missing"));

        var r = Reservation.builder()
                .customer(customer)
                .hotel(hotel)
                .room(room)
                .checkInDate(req.checkInDate())
                .checkOutDate(req.checkOutDate())
                .status(booked)
                .build();
        return reservationRepo.save(r);
    }

    /** DB-side booking via stored procedure hm.usp_CreateReservation (optional). */
    public Long bookViaProcedure(ReservationRequest req) {
        if (!req.checkOutDate().isAfter(req.checkInDate())) {
            throw new IllegalArgumentException("CheckOutDate must be after CheckInDate");
        }
        Map<String, Object> out = createResProc.execute(
                Map.of("CustomerId", req.customerId(),
                        "HotelId", req.hotelId(),
                        "RoomId", req.roomId(),
                        "CheckInDate", java.sql.Date.valueOf(req.checkInDate()),
                        "CheckOutDate", java.sql.Date.valueOf(req.checkOutDate()),
                        "ReservationId", null)
        );
        // SQL Server OUT param name comes back as "@ReservationId" or "ReservationId" depending on driver
        Object val = out.getOrDefault("ReservationId", out.get("@ReservationId"));
        if (val == null) throw new IllegalStateException("Reservation failed");
        return ((Number) val).longValue();
    }
}

