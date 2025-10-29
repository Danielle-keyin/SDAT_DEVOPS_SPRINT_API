package com.keyin.airtravel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @ManyToMany(mappedBy = "flights")
    private Set<Passenger> passengers = new HashSet<>();

    public Flight() {}

    public Flight(Aircraft aircraft, Airport origin, Airport destination, LocalDateTime departureTime) {
        this.aircraft = aircraft;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

    public Airport getOrigin() { return origin; }
    public void setOrigin(Airport origin) { this.origin = origin; }

    public Airport getDestination() { return destination; }
    public void setDestination(Airport destination) { this.destination = destination; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public Set<Passenger> getPassengers() { return passengers; }
    public void setPassengers(Set<Passenger> passengers) { this.passengers = passengers; }
}
