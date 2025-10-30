package com.keyin.airtravel.controller;

import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.repository.AirportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private final AirportRepository repo;
    public AirportController(AirportRepository repo) { this.repo = repo; }

    @GetMapping public List<Airport> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Airport one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Airport " + id + " not found"));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Airport create(@RequestBody Airport a) { return repo.save(a); }

    @PutMapping("/{id}")
    public Airport update(@PathVariable Long id, @RequestBody Airport a) {
        a.setId(id); return repo.save(a);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
