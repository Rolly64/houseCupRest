package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.repositories.ScoreRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreServiceJpa implements ScoreService{
    private ScoreRepositoryJpa scoreRepositoryJpa;
    @Autowired
    public ScoreServiceJpa(ScoreRepositoryJpa scoreRepositoryJpa) {
        this.scoreRepositoryJpa = scoreRepositoryJpa;
    }

    @Override
    public Score addScore(Score score) {
        return scoreRepositoryJpa.save(score);
    }

    @Override
    public Score save(Score score) {
        return scoreRepositoryJpa.save(score);
    }

    @Override
    public Optional<Score> findById(long id) {
        return scoreRepositoryJpa.findById(id);
    }

    @Override
    public Optional<Score> updateScore(Score score) {
        Optional<Score> oS = scoreRepositoryJpa.findById(score.getId());
        if(oS.isPresent()){
            return Optional.of(scoreRepositoryJpa.save(score));
        }
        return Optional.empty();
    }
    @Override
    public Optional<Score> deleteScoreById(long id) {
        Optional<Score> oS = scoreRepositoryJpa.findById(id);
        oS.ifPresent(score -> scoreRepositoryJpa.delete(score));
        return oS;
    }
}