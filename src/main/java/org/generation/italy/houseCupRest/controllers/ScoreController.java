package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.TeacherRepositoryJpa;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.generation.italy.houseCupRest.model.services.ScoreServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ScoreController {
    HouseService houseService;
    ScoreService scoreService;
    RegisterService registerService;

    @Autowired
    public ScoreController(HouseService houseService, ScoreService scoreService, RegisterService registerService) {
        this.houseService = houseService;
        this.scoreService = scoreService;
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> createNewScore(@RequestBody ScoreDto scoreDto, UriComponentsBuilder uribuilder) {
        Score score = scoreDto.toScore();
        try {
            scoreService.saveScore(score, scoreDto.getStudentId(), scoreDto.getTeacherId());
            URI location = uribuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(new ScoreDto(score));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateScore(@RequestBody ScoreDto scoreDto,@PathVariable long id) {
        Score score = scoreDto.toScore();
        try {
            scoreService.updateScore(score);
            return ResponseEntity.ok(scoreDto);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deleteScore(@RequestBody ScoreDto scoreDto) {
        Score score = scoreDto.toScore();
        try {
            scoreService.deleteScore(score);
            return ResponseEntity.ok((scoreDto));
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
