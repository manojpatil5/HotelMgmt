package com.hotelmanagement.hotel.repository;

import com.hotelmanagement.hotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {}

