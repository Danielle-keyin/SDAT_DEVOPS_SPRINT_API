package com.keyin.airtravel.repository;

import com.keyin.airtravel.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
