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
    JOIN s.scores sc
    WHERE s.house.id = :houseId
      AND sc.points = (
          SELECT MAX(sc2.points)
          FROM Score sc2
          WHERE sc2.student.house.id = :houseId
      )
    """)
    List<Student> findByBestSingleScoreAndHouseId(@Param("houseId") long houseId);

    @Query("""
    SELECT s
    FROM Student s
    JOIN s.scores sc
    WHERE s.house.id = :houseId and s.course.id = :classId
      AND sc.points = (
          SELECT MAX(sc2.points)
          FROM Score sc2
          WHERE sc2.student.house.id = :houseId and s.course.id = :classId
      )
    """)
    List<Student> findByBestSingleScoreAndHouseIdAndClassId(@Param("houseId") long houseId, @Param("classId") long classId);

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
    List<Student> findBestFromHouse(@Param("houseId") long houseId);

    @Query("""
    SELECT s
    FROM Student s
    JOIN s.house h
    LEFT JOIN s.scores sc
    WHERE h.id = :houseId
    AND s.course.id = :classId
    GROUP BY s.id
    HAVING SUM(sc.points) = (
        SELECT MAX(totalScore)
        FROM (
            SELECT SUM(sc.points) as totalScore
            FROM Student s2
            JOIN s2.house h2
            LEFT JOIN s2.scores sc
            WHERE h2.id = :houseId
            AND s2.course.id = :classId
            GROUP BY s2.id
        )
    )
    """)
    List<Student> findBestFromHouseAndClass(@Param("houseId") long houseId, @Param("classId") long classId);

    @Query("""
            SELECT s
            FROM Student s
            JOIN s.scores sc
            WHERE sc.motivation like %:reason%
            """)
    List<Student> findScoresByReason(@Param("reason") String reason);
}
