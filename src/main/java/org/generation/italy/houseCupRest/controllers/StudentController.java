package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.exceptions.ResourceNotFoundException;
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
    ResponseEntity<?> findStudentScores(@PathVariable long id) {
        try {
            List<Score> scores = scoreService.findStudentScores(id);
            List<ScoreDto> dtos = scores.stream().map(ScoreDto::new).toList();
            return ResponseEntity.ok(dtos);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/weeklyScores")
    ResponseEntity<?> findStudentWeeklyScores(@PathVariable long id) {
        try {
            List<Score> scores = scoreService.findStudentWeeklyScores(id);
            List<ScoreDto> dtos = scores.stream().map(ScoreDto::new).toList();
            return ResponseEntity.ok(dtos);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/house/{id}/highestScore")
    ResponseEntity<?> findStudentWithBestSingleScoreAndHouseId(@PathVariable long id) {
        try {
            List<Student> bestStudents = regService.findStudentsWithBestSingleScoreByHouseId(id);
            List<StudentDto> bestStudentsDto = bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/class/{classId}/house/{houseId}/highestScore")
    ResponseEntity<?> findStudentWithBestSingleScoreAndHouseIdAndClassId(@PathVariable long classId, @PathVariable long houseId) {
        try {
            List<Student> bestStudents = regService.findStudentsWithBestSingleScoreByHouseIdAndClassID(houseId,classId);
            List<StudentDto> bestStudentsDto = bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/house/{id}/bestStudent")
    ResponseEntity<?> findBestStudentFromHouse(@PathVariable long id) {
        try {
            List<Student> bestStudents = regService.findBestStudentsByHouseId(id);
            List<StudentDto> bestStudentsDto = bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/class/{classId}/house/{houseId}/bestStudent")
    ResponseEntity<?> findBestStudentFromHouseAndClass(@PathVariable long classId, @PathVariable long houseId) {
        try {
            List<Student> bestStudents = regService.findBestStudentsByHouseIdAndClassID(houseId,classId);
            List<StudentDto> bestStudentsDto = bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);
        } catch (IdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/score/reason/{reason}")
    ResponseEntity<?> findStudentScoresByWordInReason(@PathVariable String reason) {
        try {
            List<Student> studentsWithReason = regService.findStudentWithScoreReasonContains(reason);
            List<StudentDto> dto = studentsWithReason.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
