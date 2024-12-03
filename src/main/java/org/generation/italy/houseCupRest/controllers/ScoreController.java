package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/score")
public class ScoreController {
    private ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
//        @PostMapping
//        ResponseEntity<?> createNewScore(@RequestBody ScoreDto scoreDto, UriComponentsBuilder uriBuilder) {
//        Optional<Student> student = regService.findStudentById(scoreDto.getStudentId());
//        if(student.isEmpty()) {
//            return new ResponseEntity<>("student not found", HttpStatus.NOT_FOUND);
//        }
//        Optional<Teacher> teacher = regService.findTeacherById(scoreDto.getTeacherId());
//        if(teacher.isEmpty()) {
//            return new ResponseEntity<>("teacher not found", HttpStatus.NOT_FOUND);
//        }
//        Score score = scoreDto.toScore();
//        score.setStudent(student.get());
//        score.setTeacher(teacher.get());
//        Score addedScore = scoreService.addScore(score);
//        URI location = uriBuilder.path("/scores/{id}").buildAndExpand(score.getId()).toUri();
//        return ResponseEntity.created(location).body(new ScoreDto(score));
//    }


    @PostMapping
    public ResponseEntity<?> createNewScore(@RequestBody ScoreDto scoreDto, UriComponentsBuilder uriBuilder) {
        Score score = scoreDto.toScore();
        try {
            scoreService.saveScore(score, scoreDto.getStudentId(), scoreDto.getTeacherId());
            URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(new ScoreDto(score));

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateScore(@RequestBody ScoreDto scoreDto, @PathVariable long id) {
        if (id != scoreDto.getId()) {
            return ResponseEntity.badRequest().body("Ids don't match, can't update.");
        }
        Score score = scoreDto.toScore();
        try {
            scoreService.uptadeScore(score);
            return ResponseEntity.ok(scoreDto);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable long id) {
        try {
            scoreService.deleteScore(id);
            return ResponseEntity.noContent().build();
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
