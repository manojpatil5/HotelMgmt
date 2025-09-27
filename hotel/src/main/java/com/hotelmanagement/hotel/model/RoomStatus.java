package com.hotelmanagement.hotel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RoomStatus", schema = "hm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomStatus {
    @Id
    @Column(name = "RoomStatusId")
    private Short id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;
}