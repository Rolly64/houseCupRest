package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HouseRepositoryJpa extends JpaRepository<House, Long> {
    @Query("""
            SELECT SUM(sc.points)
            FROM House AS h
            JOIN h.students st
            JOIN st.scores sc
            WHERE h.id = :houseId
            """)
    Integer getTotalScoreByHouseId (@Param("houseId") long houseId);
}
