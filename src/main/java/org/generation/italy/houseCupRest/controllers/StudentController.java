package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    private RegisterService registerService;

    public StudentController(StudentService studentService, RegisterService registerService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<?> scoreHistoryByStudentId(@PathVariable long id){
        List<ScoreDto> dtoList = studentService.scoreHistoryByStudentId(id).stream().map(ScoreDto::fromScore).toList();
        if (!dtoList.isEmpty()) return ResponseEntity.ok(dtoList);
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/score/thisWeek")
    public ResponseEntity<?> scoreHistoryByStudentIdCurrentWeek(@PathVariable long id){
        List<ScoreDto> dtoList = studentService.scoreHistoryByStudentIdCurrentWeek(id)
                .stream().map(ScoreDto::fromScore).toList();
        if (!dtoList.isEmpty()) return ResponseEntity.ok(dtoList);
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> findStudents(@RequestParam (required = false) String motivation,
                                                         @RequestParam (required = false) Boolean byBestScore,
                                                         @RequestParam (required = false) Long houseId,
                                                         @RequestParam (required = false) Long courseId
                                                         ){
        List<Student> students = null;
        if(motivation != null && !motivation.isBlank()){
            students = studentService.findByScoresMotivationContainingIgnoreCase(motivation);
        } else if(byBestScore != null && byBestScore && courseId != null && houseId != null){
            students = studentService.findStudentsByBestSingleScoreAndHouseAndClassId(houseId, courseId);
        }else if(byBestScore != null && byBestScore) {
            students = studentService.findStudentsByBestSingleScore();
        }else {
            students = registerService.findAllStudents();
        }
        return ResponseEntity.ok(StudentDto.fromStudents(students));
    }
}