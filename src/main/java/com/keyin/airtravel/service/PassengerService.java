package com.keyin.airtravel.service;

import com.keyin.airtravel.model.*;
import com.keyin.airtravel.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PassengerService {
    private final PassengerRepository repo;

    public PassengerService(PassengerRepository repo) {
        this.repo = repo;
    }

    public List<Passenger> getAllPassengers() {
        return repo.findAll();
    }

    public Passenger getPassengerById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Passenger not found: " + id));
    }

    public Passenger createPassenger(Passenger p) {
        return repo.save(p);
    }

    public Passenger updatePassenger(Long id, Passenger p) {
        p.setId(id);
        return repo.save(p);
    }

    public void deletePassenger(Long id) {
        repo.deleteById(id);
    }

    public Set<Aircraft> getAircraftFlownByPassenger(Long passengerId) {
        Passenger p = getPassengerById(passengerId);
        return p.getFlights().stream()
                .map(Flight::getAircraft)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Airport> getAirportsUsedByPassenger(Long passengerId) {
        Passenger p = getPassengerById(passengerId);
        return p.getFlights().stream()
                .flatMap(f -> Stream.of(f.getOrigin(), f.getDestination()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
