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
    List<Student> findTopStudentSingleScoreByHouse(
            @Param("houseId") long houseId
    );

    @Query("""
            SELECT s.student
            FROM Score s
            WHERE s.student.house.id = :houseId AND s.student.course.id= :classId
            AND s.points = (
                SELECT MAX(SUM(s2.points))
                FROM Score s2
                WHERE s2.student.house.id = :houseId AND s.student.course.id= :classId
            )
           """)
    List<Student> findTopStudentSingleScoreByHouseAndByClassId(
            @Param("houseId") long houseId,
            @Param("classId") long classId
    );



    @Query("""
            SELECT s.student
            FROM Score s
            JOIN s.student st
            WHERE st.house.id=:houseId
             AND s.points=(
                 SELECT MAX(s2.points)
                 FROM Score s2
                 WHERE s2.student.house.id=:houseId
                 AND
                 s2.assignDate BETWEEN :startOfWeek AND :endOfWeek
             )
           """)
    List<Student>findBestStudentByHouseId(
            @Param("houseId") long houseId,
            @Param("startOfWeek") LocalDate startOfWeek,
            @Param("endOfWeek") LocalDate endOfWeek
    );

    @Query("""
             SELECT s.student
            FROM Score s
            JOIN s.student st
            WHERE st.house.id=:houseId AND st.course.id= :classId
                  AND s.points=(
                 SELECT MAX(s2.points)
                 FROM Score s2
                 WHERE s2.student.house.id=:houseId AND s.student.course.id=:classId
          
             )
           """)
    List<Student>findBestStudentByHouseIdAndClassId(
            @Param("houseId") long houseId,
            @Param("classId") long classId
    );

}



