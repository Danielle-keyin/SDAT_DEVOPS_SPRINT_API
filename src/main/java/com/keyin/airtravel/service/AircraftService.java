package com.keyin.airtravel.service;

import com.keyin.airtravel.model.*;
import com.keyin.airtravel.repository.AircraftRepository;
import com.keyin.airtravel.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AircraftService {
    private final AircraftRepository aircraftRepo;
    private final FlightRepository flightRepo;

    public AircraftService(AircraftRepository aircraftRepo, FlightRepository flightRepo) {
        this.aircraftRepo = aircraftRepo;
        this.flightRepo = flightRepo;
    }

    public List<Aircraft> getAllAircraft() {
        return aircraftRepo.findAll();
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Aircraft not found: " + id));
    }

    public Aircraft createAircraft(Aircraft a) {
        return aircraftRepo.save(a);
    }

    public Aircraft updateAircraft(Long id, Aircraft a) {
        a.setId(id);
        return aircraftRepo.save(a);
    }

    public void deleteAircraft(Long id) {
        aircraftRepo.deleteById(id);
    }

    public Map<String, List<Airport>> getAirportsForAircraft(Long aircraftId) {
        getAircraftById(aircraftId); // verify exists
        var origins = flightRepo.findDistinctOriginsByAircraftId(aircraftId);
        var destinations = flightRepo.findDistinctDestinationsByAircraftId(aircraftId);
        return Map.of("origins", origins, "destinations", destinations);
    }
}
