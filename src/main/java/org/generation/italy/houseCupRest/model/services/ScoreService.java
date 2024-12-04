package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;

import java.util.Optional;

public interface ScoreService {
    Score addScore(Score score);
    Score save(Score score);
    Optional<Score> findById(long id);
    public Score saveScore(Score score, long studentId, long TeacherId) throws EntityNotFoundException;
    public Score updateScore(Score s) throws IdDoesNotExistException;
    public Score deleteScore(Score s) throws IdDoesNotExistException;
}
