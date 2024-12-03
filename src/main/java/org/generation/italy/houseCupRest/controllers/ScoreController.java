package org.generation.italy.houseCupRest.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/score")
public class ScoreController {
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreDto> getScoresById(@PathVariable long id){
        Optional<Score> scores = this.scoreService.findById(id);
        return scores.map(score -> ResponseEntity.ok(new ScoreDto(score)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateScore(@RequestBody ScoreDto dto, @PathVariable long id){
        if(id != dto.getId()){
            return ResponseEntity.badRequest().body("Gli id della richiesta e del body non coincidono");
        }
        Score score = dto.toScore();
        Optional<Score> isUpdated = scoreService.updateScore(score);
        if(isUpdated.isPresent()){
            return ResponseEntity.ok(new ScoreDto(isUpdated.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScoreDto> deleteById(@PathVariable long id) {
        Optional<Score> isDeleted = scoreService.deleteScoreById(id);
        return isDeleted.map(score -> ResponseEntity.ok(new ScoreDto((score))))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){
        Score score = dto.toScore();
        try{
            scoreService.saveScore(score,dto.getStudentId(),dto.getTeacherId());
            URI location = uriBuilder.path("score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(new ScoreDto(score));
        }catch (EntityNotFoundException e){
            throw new RuntimeException(e);
        }
    }



}
