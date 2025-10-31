package com.keyin.airtravel.service;

import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AirportService {
    private final AirportRepository repo;

    public AirportService(AirportRepository repo) {
        this.repo = repo;
    }

    public List<Airport> getAllAirports() {
        return repo.findAll();
    }

    public Airport getAirportById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Airport not found: " + id));
    }

    public Airport createAirport(Airport a) {
        return repo.save(a);
    }

    public Airport updateAirport(Long id, Airport a) {
        a.setId(id);
        return repo.save(a);
    }

    public void deleteAirport(Long id) {
        repo.deleteById(id);
    }
}
