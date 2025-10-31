package com.keyin.airtravel.service;

import com.keyin.airtravel.model.Flight;
import com.keyin.airtravel.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FlightService {
    private final FlightRepository repo;

    public FlightService(FlightRepository repo) {
        this.repo = repo;
    }

    public List<Flight> getAllFlights() {
        return repo.findAll();
    }

    public Flight getFlightById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Flight not found: " + id));
    }

    public Flight createFlight(Flight f) {
        return repo.save(f);
    }

    public Flight updateFlight(Long id, Flight f) {
        f.setId(id);
        return repo.save(f);
    }

    public void deleteFlight(Long id) {
        repo.deleteById(id);
    }
}
