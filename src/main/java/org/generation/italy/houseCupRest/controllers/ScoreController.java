package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/score")
public class ScoreController {
    private RegisterService regService;
    private ScoreService scoreService;
    @Autowired
    public ScoreController(RegisterService regService, ScoreService scoreService) {
        this.regService = regService;
        this.scoreService = scoreService;
    }

//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){
//        Score score = dto.toScore();
//        Optional<Student> oSt = regService.findStudentById(dto.studentId());
//        if(oSt.isEmpty()){
//            return new ResponseEntity<>("studente non trovato", HttpStatus.NOT_FOUND);
//        }
//        Optional<Teacher> oT = regService.findTeacherById(dto.teacherId());
//        if(oT.isEmpty()){
//            return new ResponseEntity<>("insegnante non trovato", HttpStatus.NOT_FOUND);
//        }
//        score.setStudent(oSt.get());
//        score.setTeacher(oT.get());
//        scoreService.save(score);
//        URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
//        return ResponseEntity.created(location).body(ScoreDto.fromScore(score));
//    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){
        Score score = dto.toScore();
        try {
            scoreService.saveScore(score, dto.studentId(), dto.teacherId());
            URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(ScoreDto.fromScore(score));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ScoreDto scoreDto, @PathVariable long id) {
        Score score = scoreDto.toScore();
        score.setId(id);
        Optional<Score> updated = scoreService.update(score);
        if(updated.isPresent()){
            return ResponseEntity.ok(score);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        Optional<Score> oC = scoreService.deleteById(id);
        if(oC.isPresent()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> scoreHistoryByStudentId(@PathVariable long id){

        return ResponseEntity.notFound().build();
    }
}