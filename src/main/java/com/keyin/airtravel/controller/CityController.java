package com.keyin.airtravel.controller;

import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.model.City;
import com.keyin.airtravel.repository.AirportRepository;
import com.keyin.airtravel.repository.CityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityRepository cityRepo;
    private final AirportRepository airportRepo;

    public CityController(CityRepository cityRepo, AirportRepository airportRepo) {
        this.cityRepo = cityRepo; this.airportRepo = airportRepo;
    }

    @GetMapping public List<City> all() { return cityRepo.findAll(); }

    @GetMapping("/{id}")
    public City one(@PathVariable Long id) {
        return cityRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("City " + id + " not found"));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City c) { return cityRepo.save(c); }

    @PutMapping("/{id}")
    public City update(@PathVariable Long id, @RequestBody City c) {
        c.setId(id); return cityRepo.save(c);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { cityRepo.deleteById(id); }

    //Q1
    @GetMapping("/{id}/airports")
    public List<Airport> airportsInCity(@PathVariable Long id) {
        // ensures city exists → nicer 404s
        cityRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("City " + id + " not found"));
        return airportRepo.findByCityId(id);
    }
}
