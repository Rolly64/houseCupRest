package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.Score;

import java.util.List;
import java.util.Optional;

public interface ScoreService {
    Score addScore(Score score);
    Score save(Score score);
    Optional<Score> findById(long id);
    List<Score> getAllScore();
    Optional<Score> updateScore(Score score);
    Optional<Score> deleteScoreById(long id);
    Score saveScore(Score score, long studentId, long teacherId);
}
