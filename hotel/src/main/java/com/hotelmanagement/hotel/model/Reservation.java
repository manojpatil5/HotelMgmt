package com.hotelmanagement.hotel.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "hm", name = "Reservation")
//@Table(name="Reservation")
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationId")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "HotelId", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "RoomId", nullable = false)
    private Room room;

    @Column(name = "CheckInDate", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "CheckOutDate", nullable = false)
    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "StatusId", nullable = false)
    private ReservationStatus status;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;
    //Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Reservation reservation = new Reservation();

        public Builder id(Long id) {
            reservation.id = id;
            return this;
        }

        public Builder customer(Customer customer) {
            reservation.customer = customer;
            return this;
        }

        public Builder hotel(Hotel hotel) {
            reservation.hotel = hotel;
            return this;
        }

        public Builder room(Room room) {
            reservation.room = room;
            return this;
        }

        public Builder checkInDate(LocalDate checkInDate) {
            reservation.checkInDate = checkInDate;
            return this;
        }

        public Builder checkOutDate(LocalDate checkOutDate) {
            reservation.checkOutDate = checkOutDate;
            return this;
        }

        public Builder status(ReservationStatus status) {
            reservation.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            reservation.createdAt = createdAt;
            return this;
        }

        public Reservation build() {
            return reservation;
        }
    }




    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}

