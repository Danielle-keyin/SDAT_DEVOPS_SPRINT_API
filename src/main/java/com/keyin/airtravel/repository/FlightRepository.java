package com.keyin.airtravel.repository;

import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.model.Flight;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByAircraftId(Long aircraftId);

    @Query("SELECT DISTINCT f.origin FROM Flight f WHERE f.aircraft.id = :aircraftId")
    List<Airport> findDistinctOriginsByAircraftId(@Param("aircraftId") Long aircraftId);

    @Query("SELECT DISTINCT f.destination FROM Flight f WHERE f.aircraft.id = :aircraftId")
    List<Airport> findDistinctDestinationsByAircraftId(@Param("aircraftId") Long aircraftId);
}
