package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScoreService {
    Score save(Score score);
    Optional<Score> findById(long id);
    Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException;
    Score uptadeScore(Score score) throws IdDoesNotExistException;
    void deleteScore(long id) throws IdDoesNotExistException;
    List<Score> findStudentScores(long id) throws  EntityNotFoundException;
    List<Score> findStudentWeeklyScores(long id) throws EntityNotFoundException;

}
