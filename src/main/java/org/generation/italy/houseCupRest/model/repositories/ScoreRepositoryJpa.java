package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScoreRepositoryJpa extends JpaRepository<Score, Long> {
    @Query("""
       SELECT s
       FROM Score s
       WHERE s.assignDate BETWEEN :startOfWeek AND :endOfWeek
         AND s.student.id = :studentId
       """)
    List<Score> getAllScoresByStudentIdAndDateRange(
            @Param("studentId") long studentId,
            @Param("startOfWeek") LocalDate startOfWeek,
            @Param("endOfWeek") LocalDate endOfWeek
    );


}
