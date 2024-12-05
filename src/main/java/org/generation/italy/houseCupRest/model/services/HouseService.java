package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.dtos.HouseRankingDto;
import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseService {
   List<HouseRanking> getRankings();
   List<Student> bestStudentsByHouseId(long id);
   List<Student> bestStudentsByHouseAndClassId (long houseId, long courseId);
}
