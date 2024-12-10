package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScoreRepositoryJpa extends JpaRepository<Score, Long> {
    List<Score> findByStudentId (Long id);
    List<Score> findByStudentIdAndAssignDateBetween(Long id, LocalDate ld1, LocalDate ld2);
}
