package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;

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
    List<Student> findTopScorerByHouse(long id);
}