package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdNotFound;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScoreService {

    Score save(Score score);
    Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException;
    Optional<Score> findById(long id);
    Optional<Score> deleteById(long id);
    Optional<Score> updateScore(Score score);
    List<Score> findStudentScores(long id,LocalDate startDate,LocalDate endDate) throws EntityNotFoundException;
    List<Student> findTopStudentSingleScoreByHouse(long id) throws IdNotFound;
    List<Student> findTopStudentSingleScoreByHouseAndByClassId(Long houseId,Long courseId) throws IdNotFound;
    List<Student>findBestStudentByHouseId(long id, LocalDate starDate,LocalDate endDate) throws IdNotFound;
    List<Student>findBestStudentByHouseIdAndClassId(long houseId,long classId) throws IdNotFound;
    List<Student> findDistinctStudentByMotivationContaining(String word);


}