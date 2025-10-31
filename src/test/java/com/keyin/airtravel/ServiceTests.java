package com.keyin.airtravel;

import com.keyin.airtravel.model.*;
import com.keyin.airtravel.repository.PassengerRepository;
import com.keyin.airtravel.repository.FlightRepository;
import com.keyin.airtravel.repository.AircraftRepository;
import com.keyin.airtravel.service.PassengerService;
import com.keyin.airtravel.service.AircraftService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ServiceTests {

    @Mock PassengerRepository passengerRepo;
    @Mock FlightRepository flightRepo;
    @Mock AircraftRepository aircraftRepo;

    @InjectMocks PassengerService passengerService;
    @InjectMocks AircraftService aircraftService;

    @Test
    void passengerService_returnsAircraftAndAirports() {
        Passenger p = new Passenger();
        p.setId(1L);

        Aircraft ac = new Aircraft(); ac.setId(10L); ac.setType("B737");
        Airport yyt = new Airport(); yyt.setId(1L); yyt.setCode("YYT"); yyt.setName("St. John's Intl");
        Airport yhz = new Airport(); yhz.setId(2L); yhz.setCode("YHZ"); yhz.setName("Halifax Stanfield");

        Flight f1 = new Flight(); f1.setId(100L); f1.setAircraft(ac); f1.setOrigin(yyt); f1.setDestination(yhz);

        p.getFlights().add(f1);

        when(passengerRepo.findById(1L)).thenReturn(Optional.of(p));

        Set<Aircraft> aircraft = passengerService.getAircraftFlownByPassenger(1L);
        Set<Airport> airports  = passengerService.getAirportsUsedByPassenger(1L);

        assertThat(aircraft).extracting(Aircraft::getType).contains("B737");
        assertThat(airports).extracting(Airport::getCode).contains("YYT","YHZ");
    }

    @Test
    void aircraftService_returnsOriginsAndDestinations() {
        Aircraft ac = new Aircraft(); ac.setId(10L);

        Airport yyt = new Airport(); yyt.setId(1L); yyt.setCode("YYT");
        Airport yhz = new Airport(); yhz.setId(2L); yhz.setCode("YHZ");

        when(aircraftRepo.findById(10L)).thenReturn(Optional.of(ac));
        when(flightRepo.findDistinctOriginsByAircraftId(10L)).thenReturn(List.of(yyt));
        when(flightRepo.findDistinctDestinationsByAircraftId(10L)).thenReturn(List.of(yhz));

        var map = aircraftService.getAirportsForAircraft(10L);
        assertThat(map.get("origins")).extracting(Airport::getCode).contains("YYT");
        assertThat(map.get("destinations")).extracting(Airport::getCode).contains("YHZ");
    }
}
