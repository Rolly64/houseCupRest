package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    ScoreService scoreService;
    @Autowired
    public StudentController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    @GetMapping("/{id}/score")
    ResponseEntity<?> getStudentScores(@PathVariable long id) {
        try {
            List<Score> scores = scoreService.findStudentScores(id);
            List<ScoreDto> dtos = scores.stream().map(ScoreDto::new).toList();
            return ResponseEntity.ok(dtos);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
