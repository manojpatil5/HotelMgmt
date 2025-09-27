package com.hotelmanagement.hotel.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationRequest(
        @NotNull Integer customerId,
        @NotNull Integer hotelId,
        @NotNull Integer roomId,
        @NotNull @Future LocalDate checkInDate,
        @NotNull @Future LocalDate checkOutDate
) {}
