package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepositoryJpa extends JpaRepository<House, Long> {
}
