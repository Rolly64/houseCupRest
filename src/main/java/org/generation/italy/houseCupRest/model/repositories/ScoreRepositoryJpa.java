package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepositoryJpa extends JpaRepository<Score, Long> {
}
