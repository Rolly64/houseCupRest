package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;

import java.util.Optional;

public interface ScoreService {
    Score setScoreWithStudentAndTeacher(Score score, long studentId, long teacherId) throws EntityNotFoundException;
    Score addScore(Score score);
    Score save(Score score);
    Optional<Score> findById(long id);
    Optional<Score> updateScore(Score score);
    Score createScore(Score score);
    Optional<Score> deleteById(long id);
}
