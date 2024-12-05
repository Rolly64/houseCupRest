package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    @Query("""
        SELECT s
        FROM Student s
        JOIN s.house h
        LEFT JOIN s.scores sc
        WHERE h.id = :houseId
        GROUP BY s.id
        HAVING SUM(sc.points) = (
            SELECT MAX(totalScore)
            FROM (
                SELECT SUM(sc.points) as totalScore
                FROM Student s2
                JOIN s2.house h2
                LEFT JOIN s2.scores sc
                WHERE h2.id = :houseId
                GROUP BY s2.id
            )
        )
        """)
            List<Student> findBestStudentsByHouse (@Param("houseId") long houseId);


    @Query("""
        SELECT s
        FROM Student s
        JOIN s.house h
        LEFT JOIN s.scores sc
        WHERE h.id = :houseId
        AND s.course.id = :courseId
        GROUP BY s.id
        HAVING SUM(sc.points) = (
            SELECT MAX(totalScore)
            FROM (
                SELECT SUM(sc.points) as totalScore
                FROM Student s2
                JOIN s2.house h2
                LEFT JOIN s2.scores sc
                WHERE h2.id = :houseId
                AND s2.course.id = :courseId
                GROUP BY s2.id
            )
        )
        """)
    List<Student> findBestStudentByCourseAndHouse(@Param("courseId")long courseId, @Param("houseId")long houseId);

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
    List<Student> findStudentsByBestSingleScoreAndHouseAndClass(@Param("courseId") long courseId, @Param("houseId") long houseId);
}
