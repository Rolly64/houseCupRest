package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreRepositoryJpa extends JpaRepository<Score, Long> {
    @Query("""
            SELECT s 
            FROM Score s 
            WHERE s.student.id = :studentId
       """)
    List<Score> findScoresByStudentId(@Param("studentId") Long studentId);

}
