package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.StudentMvp;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScoreRepositoryJpa extends JpaRepository<Score, Long> {
    @Query("""
            SELECT s
            FROM Score s
            WHERE s.student.id = :studentId
            """)
    List<Score> findScoresByStudentId(@Param("studentId") Long studentId);

    @Query("""
            SELECT s
            FROM Score s
            WHERE s.assign_date BETWEEN :startOfWeek AND :endOfWeek
            AND s.student.id = :studentId
            """)
    List<Score> findCurrentWeekScore(@Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek")  LocalDate endOfWeek, @Param("studentId") Long studentId);

    @Query("""
            SELECT new org.generation.italy.houseCupRest.model.StudentMvp(s.student.firstname, s.student.surname)
            FROM Score AS s
            JOIN s.student AS st
            WHERE st.house.id = :houseId
            GROUP BY s.student.id, s.student.firstname, s.student.surname
            ORDER BY SUM(s.points) DESC
            """)
    List<StudentMvp> findMvpByHouseId(@Param("houseId") Long houseId);
    @Query("""
        SELECT s.student
        FROM Score AS s
        JOIN s.student AS st
        WHERE s.motivation LIKE %:keyWord%
        """)
    List<Student> findStudentByKeyWord(@Param("keyWord") String keyWord);
    @Query("""
        SELECT s.student
        FROM Score s
        WHERE s.points = (SELECT MAX(s2.points) FROM Score s2)
        """)
    List<Student> findHighestSingleScorerBySingleScore();
    @Query("""
     SELECT s.student
     FROM Score s
     JOIN s.student AS st
     WHERE st.house.id = :houseId
       AND (:courseId IS NULL OR st.course.id = :courseId)
       AND s.points = (SELECT MAX(s2.points)
                       FROM Score s2
                       WHERE s2.student.house.id = :houseId
                         AND (:courseId IS NULL OR s2.student.course.id = :courseId))
     """)
    List<Student> findTheBestStudentsByClassAndHouseId(@Param("houseId") Long houseId, @Param("courseId") Long courseId);



}
