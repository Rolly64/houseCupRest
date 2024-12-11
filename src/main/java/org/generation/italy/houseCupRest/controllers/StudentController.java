package org.generation.italy.houseCupRest.controllers;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    private RegisterService regsService;
    @Autowired
    public StudentController(StudentService studentService, RegisterService regsService) {
        this.studentService = studentService;
        this.regsService = regsService;
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllScores() {
        List<Student> students = this.studentService.getAllStudent();
        List<StudentDto> dto = students.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(dto);
    }
}