package com.hotelmanagement.hotel.repository;

import com.hotelmanagement.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {}

