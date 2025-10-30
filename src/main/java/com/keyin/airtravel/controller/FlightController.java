package com.keyin.airtravel.controller;

import com.keyin.airtravel.model.Flight;
import com.keyin.airtravel.repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightRepository repo;
    public FlightController(FlightRepository repo) { this.repo = repo; }

    @GetMapping public List<Flight> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Flight one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Flight " + id + " not found"));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Flight create(@RequestBody Flight f) { return repo.save(f); }

    @PutMapping("/{id}")
    public Flight update(@PathVariable Long id, @RequestBody Flight f) {
        f.setId(id); return repo.save(f);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
