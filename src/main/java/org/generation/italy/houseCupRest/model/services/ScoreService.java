package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;

import java.util.Optional;

public interface ScoreService {
    Score save(Score score);
    Optional<Score> findById(long id);
    Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException;
    Score updateScore(Score score);
    boolean deleteScoreById(long id);

}
