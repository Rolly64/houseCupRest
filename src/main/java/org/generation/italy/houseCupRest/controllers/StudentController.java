package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.services.RegisterService;
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
    RegisterService regService;
    @Autowired
    public StudentController(ScoreService scoreService, RegisterService regService) {
        this.scoreService = scoreService;
        this.regService = regService;
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
    @GetMapping("/{id}/weeklyScores")
    ResponseEntity<?> getStudentWeeklyScores(@PathVariable long id) {
        try {
            List<Score> scores = scoreService.findStudentWeeklyScores(id);
            List<ScoreDto> dtos = scores.stream().map(ScoreDto::new).toList();
            return ResponseEntity.ok(dtos);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/house/{id}/bestStudents")
    ResponseEntity<?> getBestStudentsByHouseId(@PathVariable long id){
        List<Student> bestStudents = regService.findBestStudentsByHouseId(id);
        List<StudentDto> bestStudentsDto = bestStudents.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(bestStudentsDto);
    }
    @GetMapping("/class/{classId}/house/{Id}/bestStudents")
    ResponseEntity<?> findStudentWithBestSingleScoreAndHouseIdAndClassId(@PathVariable long houseId, @PathVariable long classId){
        try {
            List<Student> bestStudents = regService.findBestStudentByHouseIdAndClassId(houseId,classId);
            List<StudentDto> bestStudentsDto = bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
