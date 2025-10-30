package com.keyin.airtravel.controller;

import com.keyin.airtravel.model.Aircraft;
import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.model.Flight;
import com.keyin.airtravel.model.Passenger;
import com.keyin.airtravel.repository.PassengerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerRepository repo;
    public PassengerController(PassengerRepository repo) { this.repo = repo; }

    @GetMapping public List<Passenger> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Passenger one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger " + id + " not found"));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Passenger create(@RequestBody Passenger p) { return repo.save(p); }

    @PutMapping("/{id}")
    public Passenger update(@PathVariable Long id, @RequestBody Passenger p) {
        p.setId(id); return repo.save(p);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repo.deleteById(id); }

    // Q2
    @GetMapping("/{id}/aircraft")
    public Set<Aircraft> aircraftFlown(@PathVariable Long id) {
        Passenger p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger " + id + " not found"));
        return p.getFlights().stream().map(Flight::getAircraft).collect(Collectors.toCollection(LinkedHashSet::new));
    }
    // Q4
    @GetMapping("/{id}/airports")
    public Set<Airport> airportsUsed(@PathVariable Long id) {
        Passenger p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passenger " + id + " not found"));
        return p.getFlights().stream()
                .flatMap(f -> Stream.of(f.getOrigin(), f.getDestination()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
