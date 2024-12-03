package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/score")
public class ScoreController {
    private final ScoreService sService;
    @Autowired
    public ScoreController(ScoreService sService) {
        this.sService = sService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ScoreDto> getScoresById(@PathVariable long id){
        Optional<Score> scores = this.sService.findById(id);
        return scores.map(score -> ResponseEntity.ok(new ScoreDto(score)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ScoreDto> updateScore(@RequestBody ScoreDto score, @PathVariable long id){

    }
}
