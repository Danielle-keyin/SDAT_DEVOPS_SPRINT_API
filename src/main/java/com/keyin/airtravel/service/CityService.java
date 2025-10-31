package com.keyin.airtravel.service;

import com.keyin.airtravel.model.Airport;
import com.keyin.airtravel.model.City;
import com.keyin.airtravel.repository.AirportRepository;
import com.keyin.airtravel.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CityService {
    private final CityRepository cityRepo;
    private final AirportRepository airportRepo;

    public CityService(CityRepository cityRepo, AirportRepository airportRepo) {
        this.cityRepo = cityRepo;
        this.airportRepo = airportRepo;
    }

    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    public City getCityById(Long id) {
        return cityRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("City not found: " + id));
    }

    public City createCity(City city) {
        return cityRepo.save(city);
    }

    public City updateCity(Long id, City updated) {
        updated.setId(id);
        return cityRepo.save(updated);
    }

    public void deleteCity(Long id) {
        cityRepo.deleteById(id);
    }

    public List<Airport> getAirportsInCity(Long cityId) {
        return airportRepo.findByCityId(cityId);
    }
}
