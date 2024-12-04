package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScoreRepositoryJpa extends JpaRepository<Score, Long> {
    List<Score> findByStudentIdAndAssignDateAfterAndAssignDateBefore(long id, LocalDate d1, LocalDate d2);

    List<Score> findByStudentIdAndAssignDateAfter(long studentId, LocalDate startDate);

    List<Score> findByStudentIdAndAssignDateBefore(long studentId, LocalDate endDate);

    List<Score> findByStudentId(long studentId);

    @Query("""
            SELECT s.student
            FROM Score s
            WHERE s.student.house.id = :houseId
            AND s.points = (
                SELECT MAX(SUM(s2.points))
                FROM Score s2
                WHERE s2.student.house.id = :houseId
            )
           """)
    List<Student> findTopScoreStudentsByHouse(
            @Param("houseId") long houseId
    );
    @Query("""
            SELECT s.student
            FROM Score s
            WHERE s.student.house.id = :houseId
            AND s.points = (
                SELECT MAX(s2.points)
                FROM Score s2
                WHERE s2.student.house.id = :houseId
                AND s2.assignDate BETWEEN :startOfWeek AND :endOfWeek
            )
            AND s.assignDate BETWEEN :startOfWeek AND :endOfWeek
           """)
    List<Student> findTopScoringStudentsByHouseAndAssignDateAfterAndAssignDateBefore(
            @Param("houseId") long houseId,
            @Param("startOfWeek") LocalDate startOfWeek,
            @Param("endOfWeek") LocalDate endOfWeek
    );
    @Query("""
            SELECT s.student, SUM(s.points)
             FROM Score s
             JOIN s.student.house h
             WHERE h.id = :houseId
             GROUP BY s.student
             HAVING SUM(s.points) = (
                 SELECT MAX(SUM(s2.points))
                 FROM Score s2
                 JOIN s2.student.house h2
                 WHERE h2.id = :houseId
                 GROUP BY s2.student
             )
           """)
    List<Student> findTopScoringStudentsByHouse(
            @Param("houseId") long houseId
    );
    @Query("""
            SELECT s.student, SUM(s.points)
             FROM Score s
             JOIN s.student.house h
             WHERE h.id = :houseId
             AND s.assignDate BETWEEN :startOfWeek AND :endOfWeek
             GROUP BY s.student
             HAVING SUM(s.points) = (
                 SELECT MAX(SUM(s2.points))
                 FROM Score s2
                 JOIN s2.student.house h2
                 WHERE h2.id = :houseId
                 AND s2.assignDate BETWEEN :startOfWeek AND :endOfWeek
                 GROUP BY s2.student
             )
           """)
    List<Student> findTopScoreStudentsByHouseAndAssignDateAfterAndAssignDateBefore(
            @Param("houseId") long houseId,
            @Param("startOfWeek") LocalDate startOfWeek,
            @Param("endOfWeek") LocalDate endOfWeek
    );

}



