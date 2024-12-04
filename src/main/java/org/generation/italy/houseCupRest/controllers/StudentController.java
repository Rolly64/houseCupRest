package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    ScoreService scoreService;
    RegisterService registerService;
    HouseService houseService;

    public StudentController(ScoreService scoreService, RegisterService registerService, HouseService houseService) {
        this.scoreService = scoreService;
        this.registerService = registerService;
        this.houseService = houseService;
    }

    @GetMapping("/{id}/score")
    ResponseEntity<?> getStudentScoresById(@PathVariable long id){
        try{
            List<Score> scores = scoreService.findStudentScores(id);
            List<ScoreDto> dtos = scores.stream().map(ScoreDto::new).toList();
            return ResponseEntity.ok(dtos);

        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/weeklyScores")
    ResponseEntity<?> getStudentWeeklyScoresById(@PathVariable long id){
        try{
            List<Score> scores = scoreService.findStudentWeeklyScores(id);
            List<ScoreDto> dtos = scores.stream().map(ScoreDto::new).toList();
            return ResponseEntity.ok(dtos);

        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/house/{id}/bestStudent")
    ResponseEntity<?> getBestStudentByHouseId(@PathVariable long id){
        try{
            List<Student> bestStudents=registerService.findBestStudentByHouseId(id);
            List<StudentDto> bestStudentsDto=bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);

        }catch(IdDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/class/{classId}/house/{houseId}/bestStudent")
    ResponseEntity<?> getBestStudentByHouseIdAndClassId(@PathVariable long houseId, @PathVariable long classId){
        try{
            List<Student> bestStudents=registerService.findBestStudentByHouseIdAndClassId(houseId,classId);
            List<StudentDto> bestStudentsDto=bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);

        }catch(IdDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/house/{id}/highestScore")
    ResponseEntity<?> getStudentWithBestSingleScoreByHouseId(@PathVariable long id){
        try{
            List<Student> bestStudents = registerService.findStudentWithBestSingleScoreByHouseId(id);
            List<StudentDto> bestStudentsDto=bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);

        }catch(IdDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/class/{classId}/house/{houseId}/highestScore")
    ResponseEntity<?> getStudentWithBestSingleScoreByHouseIdAndClassId(@PathVariable long houseId, @PathVariable long classId){
        try{
            List<Student> bestStudents = registerService.findStudentWithBestSingleScoreByHouseIdAndClassId(houseId,classId);
            List<StudentDto> bestStudentsDto = bestStudents.stream().map(StudentDto::new).toList();
            return ResponseEntity.ok(bestStudentsDto);

        }catch(IdDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
