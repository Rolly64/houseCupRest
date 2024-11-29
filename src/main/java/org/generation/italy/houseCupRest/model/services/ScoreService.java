package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;

import java.util.Optional;

public interface ScoreService {
    Score addScore(Score score);
    Score save(Score score);
    Optional<Score> findById(long id);
}
