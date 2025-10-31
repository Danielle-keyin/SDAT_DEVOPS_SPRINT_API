package com.keyin.airtravel.repository;

import com.keyin.airtravel.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
