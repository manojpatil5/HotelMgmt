package com.hotelmanagement.hotel.model;


import jakarta.persistence.*;
import lombok.*;

@Entity @Table(schema = "hm", name = "ReservationStatus")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReservationStatus {
    @Id
    @Column(name = "StatusId")
    private Short id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;
}
