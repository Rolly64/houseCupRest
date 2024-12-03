package org.generation.italy.houseCupRest.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

public class ScoreController {

    ScoreService scoreService;
    RegisterService registerService;

    @Autowired
    public ScoreController(ScoreService scoreService, RegisterService registerService) {
        this.scoreService = scoreService;
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder) {

        Score score = dto.toScore();
        Optional<Student> studentOpt = registerService.findStudentById(dto.getStudentId());

        if (studentOpt.isEmpty()) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        Optional<Teacher> teacherOpt = registerService.findTeacherById(dto.getTeacherId());

        if (teacherOpt.isEmpty()) {
            return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);

        }
            score.setStudent(studentOpt.get());
            score.setTeacher(teacherOpt.get());
            scoreService.save(score);
            URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(ScoreDto.fromScore(score));
    }
    @PostMapping
    public ResponseEntity<?> createV2(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder) {
        Score score = dto.toScore();
        try{
            scoreService.save(score);
            URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(ScoreDto.fromScore(score));
        }catch (EntityNotFoundException e){
            throw new RuntimexException(e);
        }
    }
}
