package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/score")
public class ScoreController {
    ScoreService scoreService; //creazione istanza del servizio
    public ScoreController(ScoreService scoreService) { //iniezione del servizio nel costruttore
        this.scoreService = scoreService;
    }
    @PostMapping("/assign")
    public ResponseEntity<Score> addScore(@RequestBody Score scoreDto) {
        Score score = new Score();
        score.setPoints(scoreDto.getPoints());
        score.setMotivation(scoreDto.getMotivation());
        score.setAssignDate(LocalDate.now());
        Score savedScore = scoreService.addScore(score);
        return ResponseEntity.ok(score);
    }
}