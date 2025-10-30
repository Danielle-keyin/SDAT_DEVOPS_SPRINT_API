package com.keyin.airtravel.controller;

import com.keyin.airtravel.model.Aircraft;
import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.repository.AircraftRepository;
import com.keyin.airtravel.repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    private final AircraftRepository aircraftRepo;
    private final FlightRepository flightRepo;

    public AircraftController(AircraftRepository aircraftRepo, FlightRepository flightRepo) {
        this.aircraftRepo = aircraftRepo; this.flightRepo = flightRepo;
    }

    @GetMapping public List<Aircraft> all() { return aircraftRepo.findAll(); }

    @GetMapping("/{id}")
    public Aircraft one(@PathVariable Long id) {
        return aircraftRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aircraft " + id + " not found"));
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Aircraft create(@RequestBody Aircraft a) { return aircraftRepo.save(a); }

    @PutMapping("/{id}")
    public Aircraft update(@PathVariable Long id, @RequestBody Aircraft a) {
        a.setId(id); return aircraftRepo.save(a);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { aircraftRepo.deleteById(id); }

    //Q3
    @GetMapping("/{id}/airports")
    public Map<String, List<Airport>> airportsForAircraft(@PathVariable Long id) {
        aircraftRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aircraft " + id + " not found"));
        var origins = flightRepo.findDistinctOriginsByAircraftId(id);
        var destinations = flightRepo.findDistinctDestinationsByAircraftId(id);
        return Map.of("origins", origins, "destinations", destinations);
    }
}
