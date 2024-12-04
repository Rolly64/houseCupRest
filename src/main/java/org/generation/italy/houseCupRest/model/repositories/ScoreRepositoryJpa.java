package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScoreRepositoryJpa extends JpaRepository<Score, Long> {
List<Score> findByStudentId(long id);
List<Score> findByStudentIdAndAssignDateBetween(long id, LocalDate d1, LocalDate d2);
}

