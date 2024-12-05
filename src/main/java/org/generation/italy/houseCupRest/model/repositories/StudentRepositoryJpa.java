package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    @Query("""
            SELECT s
            FROM Score s
            WHERE s.student.id = :studentId
            """)
    List<Score> scoreHistoryByStudentId(@Param("studentId") long studentId);
    List<Student> findByScoresMotivationContainingIgnoreCase(String word);
    @Query("""
            SELECT s
            FROM Student s
            JOIN s.scores sc
            WHERE sc.points = (
                SELECT MAX(sc2.points)
                FROM Score sc2
            )
            """)
    List<Student> findStudentsByBestSingleScore();
    @Query("""
            SELECT s
            FROM Student s
            JOIN s.scores sc
            WHERE s.course.id = :courseId
            AND s.house.id = :houseId
            AND sc.points = (
                SELECT MAX(sc2.points)
                FROM Student s2
                JOIN s2.scores sc2
                WHERE s2.course.id = :courseId
                AND s2.house.id = :houseId
            )
            """)
    List<Student> findStudentsByBestSingleScoreAndHouseAndClassId(@Param("houseId") long houseId, @Param("courseId") long courseId);
}