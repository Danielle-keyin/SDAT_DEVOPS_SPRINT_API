package com.keyin.airtravel.repository;

import com.keyin.airtravel.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByCityId(Long cityId);
}