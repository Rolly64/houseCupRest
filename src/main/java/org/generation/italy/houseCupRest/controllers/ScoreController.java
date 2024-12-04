package org.generation.italy.houseCupRest.controllers;
import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/score")
public class ScoreController {
    private  ScoreService sService;
    private RegisterService regsService;
    @Autowired
    public ScoreController(ScoreService sService, RegisterService regsService) {
        this.sService = sService;
        this.regsService = regsService;
    }
    @GetMapping
    public ResponseEntity<List<ScoreDto>> getAllScores() {
        List<Score> scores = this.sService.getAllScore();
        List<ScoreDto> sDto = scores.stream().map(ScoreDto::new).toList();
        return ResponseEntity.ok(sDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ScoreDto> getScoresById(@PathVariable long id){
        Optional<Score> scores = this.sService.findById(id);
        if (scores.isPresent()){
            return ResponseEntity.ok(new ScoreDto(scores.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){
        Score score = dto.toScore();
        try{
            sService.saveScore(score,dto.getStudentId(),dto.getTeacherId());
            URI location = uriBuilder.path("score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(new ScoreDto(score));
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@RequestBody ScoreDto dto, @PathVariable long id) {
        if (id != dto.getId()) {
            return ResponseEntity.badRequest().body("Gli ID della richiesta e del body non coincidono");
        }
        Score score = dto.toScore();
        Optional<Score> isUpdated = sService.updateScore(score);
        if (isUpdated.isPresent()) {
            return ResponseEntity.ok(new ScoreDto(isUpdated.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ScoreDto> deleteById(@PathVariable long id) {
        Optional<Score> isDeleted = sService.deleteScoreById(id);
        return isDeleted.map(score -> ResponseEntity.ok(new ScoreDto((score))))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("student/{id}/points")
    public ResponseEntity<List<ScoreDto>> findPointsByStudentId(@PathVariable long id){
         List<Score> points = sService.findPointsByStudentId(id);
         List<ScoreDto> pointsDto = points.stream().map(ScoreDto::new).toList();
         return ResponseEntity.ok(pointsDto);
    }
    @GetMapping("student/{id}/points/weekly")
    public ResponseEntity<List<ScoreDto>> findStudentWeekScores(@PathVariable long id) {
        List<Score> points = sService.findStudentWeekScores(id);
        List<ScoreDto> pointsDto = points.stream().map(ScoreDto::new).toList();
        return ResponseEntity.ok(pointsDto);
    }
}