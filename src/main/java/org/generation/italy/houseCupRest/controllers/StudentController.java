package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
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
}