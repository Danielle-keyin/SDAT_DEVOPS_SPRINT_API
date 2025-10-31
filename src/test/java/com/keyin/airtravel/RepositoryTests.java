package com.keyin.airtravel;

import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.model.Flight;
import com.keyin.airtravel.repository.AirportRepository;
import com.keyin.airtravel.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RepositoryTests {

    @Autowired
    AirportRepository airportRepo;

    @Autowired
    FlightRepository flightRepo;

    @Test
    void findByCityId_returnsAirports() {
        List<Airport> airports = airportRepo.findByCityId(1L);
        assertThat(airports).isNotEmpty();
        assertThat(airports).extracting(Airport::getCode).contains("YYT");
    }

    @Test
    void distinctAirportsByAircraft_queriesWork() {
        List<Airport> origins = flightRepo.findDistinctOriginsByAircraftId(1L);
        List<Airport> dests   = flightRepo.findDistinctDestinationsByAircraftId(1L);
        assertThat(origins).extracting(Airport::getCode).contains("YYT");
        assertThat(dests).extracting(Airport::getCode).contains("YHZ");
    }

    @Test
    void findByAircraftId_returnsFlights() {
        List<Flight> flights = flightRepo.findByAircraftId(1L);
        assertThat(flights).isNotEmpty();
    }
}
