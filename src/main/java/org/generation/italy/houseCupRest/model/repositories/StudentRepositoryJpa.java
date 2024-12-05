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
         FROM Student s 
         JOIN s.scores sc
         WHERE s.house.id = :houseId
         AND sc.points = (
         SELECT MAX(sc2.points)
         FROM Score sc2
         WHERE sc2.student.house.id = :houseId
         """)
    List<Student> findBestStudentByHouseId(@Param("houseId") long houseId);
    @Query("""
         SELECT s
         FROM Student s 
         JOIN s.scores sc
         WHERE s.house.id = :houseId and s.course.id = :class
         AND sc.points = (
         SELECT MAX(sc2.points)
         FROM Score sc2
         WHERE sc2.student.house.id = :houseId
         """)
    List<Student> findByBestSingleScoreAndHouseIdAndClassId(@Param("houseId") long houseId,@Param("classId") long classId);

    @Query("""
            SELECT s
            FROM Student s
            JOIN s.scores sc
            WHERE s.house.id = :houseId           
            """)


    List<Student> findBestFromHouse(@Param("houseId")long houseId);

}
