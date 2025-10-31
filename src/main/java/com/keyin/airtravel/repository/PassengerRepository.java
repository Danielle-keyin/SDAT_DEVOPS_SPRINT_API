package com.keyin.airtravel.repository;

import com.keyin.airtravel.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
