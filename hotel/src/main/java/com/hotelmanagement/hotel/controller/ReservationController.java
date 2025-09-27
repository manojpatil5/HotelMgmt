package com.hotelmanagement.hotel.controller;

import com.hotelmanagement.hotel.dto.ReservationRequest;
import com.hotelmanagement.hotel.model.Reservation;
import com.hotelmanagement.hotel.repository.ReservationRepository;
import com.hotelmanagement.hotel.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public List<Reservation> list() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public Reservation create(@Valid @RequestBody ReservationRequest req) {
        return reservationService.book(req); // Java-side overlap check
        // If you want to use stored procedure instead:
        // Long id = reservationService.bookViaProcedure(req);
        // return reservationRepository.findById(id).orElse(null);
    }
}