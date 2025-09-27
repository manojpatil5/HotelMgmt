package com.hotelmanagement.hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RoomType", schema = "hm")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {
    @Id
    @Column(name = "RoomTypeId")
    private Short id;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;
}