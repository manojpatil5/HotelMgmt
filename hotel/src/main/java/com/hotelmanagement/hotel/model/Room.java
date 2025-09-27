package com.hotelmanagement.hotel.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Room", schema = "hm")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomId")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "HotelId", nullable = false)
    private Hotel hotel;

    @Column(name = "RoomNumber", nullable = false)
    private String roomNumber;

    @ManyToOne @JoinColumn(name = "RoomTypeId", nullable = false)
    private RoomType roomType;

    @Column(name = "PricePerNight", nullable = false)
    private BigDecimal pricePerNight;

    @Column(name = "Capacity", nullable = false)
    private Integer capacity;

    @ManyToOne @JoinColumn(name = "RoomStatusId", nullable = false)
    private RoomStatus status;


    //Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}