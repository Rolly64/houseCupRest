package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    //Richiesta dei migliori studenti di una determinata casa. (Indipendentemente dalla classe)
    @Query("""
            SELECT s
            FROM student s
            JOIN s.scores sc
            WHERE s.house.id = :house.id
                AND sc.points=(
                    SELECT MAX(SUM(sc2.points))
                    FROM score sc2
                    WHERE sc2.student.house.id=:houseId
                )
            """)
    List<Student> findBestByHouseId(@Param("houseId") long houseId);

    //Richiesta dei migliori studenti di una determinata casa e determinata classe.
    @Query("""
            SELECT s
            FROM student s
            JOIN s.scores sc
            WHERE s.house.id = :house.id and s.course.id = :classId
                AND sc.points=(
                    SELECT MAX(SUM(sc2.points))
                    FROM score sc2
                    WHERE sc2.student.house.id=:houseId and s.course.id = :classId
                )
            """)
    List<Student> findBestByHouseIdAndClassId(@Param("houseId") long houseId, @Param("classId") long classId);

    //Lista di tutti gli studenti che hanno uno score che contiene una certa parola chiave nella reason.
    @Query("""
            SELECT s
            FROM student s
            JOIN s.scores sc
            WHERE sc.motivation like :reason
            """)
    List<Student> findScoreByWordInReason(@Param("reason") String reason);

    //Lista di tutti gli studenti che hanno fatto un singolo score più grande di tutti. (Indipendentemente dalla casata/classe)
    @Query("""
            SELECT s
            FROM student s
            JOIN s.scores sc
            WHERE s.house.id = :house.id
                AND sc.points=(
                    SELECT MAX(sc2.points)
                    FROM score sc2
                    WHERE sc2.student.house.id=:houseId
                )
            """)
    List<Student> findByBestSingleScoreAndHouseId(@Param("houseId") long houseId);

    // Lista di tutti gli studenti che hanno fatto un singolo score più grande di tutti di una determinata casa e determinata classe.
    @Query("""
            SELECT s
            FROM student s
            JOIN s.scores sc
            WHERE s.house.id = :house.id and s.course.id = :classId
                AND sc.points=(
                    SELECT MAX(sc2.points)
                    FROM score sc2
                    WHERE sc2.student.house.id=:houseId and s.course.id = :classId
                )
            """)
    List<Student> findByBestSingleScoreAndHouseIdAndClassId(@Param("houseId") long houseId, @Param("classId") long classId);

}
