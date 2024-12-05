package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepositoryJpa extends JpaRepository<House, Long> {
    @Query("""
            SELECT SUM(sc.points)
            FROM House AS h
            JOIN h.students st
            JOIN st.scores sc
            WHERE h.id = :houseId
            """)
    Integer getTotalScoreByHouseId (@Param("houseId") long houseId);

//    @Query("""
//            SELECT st
//            FROM House AS h
//            JOIN h.students st
//            LEFT JOIN st.scores sc
//            WHERE h.id = :houseId
//            GROUP BY st
//            ORDER BY SUM(sc.points) DESC
//            """)
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
    List<Student> bestStudentsByHouseId (@Param("houseId") long houseId);

    @Query("""
            SELECT s
            FROM Student s
            JOIN s.house h
            JOIN s.class c
            LEFT JOIN s.scores sc
            WHERE h.id = :houseId
            AND c.id = :classId
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
    List<Student> bestStudentsByHouseAndClassId (@Param("houseId") long houseId, @Param("courseId") long courseId);
}