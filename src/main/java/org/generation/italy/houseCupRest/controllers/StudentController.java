package org.generation.italy.houseCupRest.controllers;
import org.generation.italy.houseCupRest.dtos.StudentDetailDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/student")
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
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentDetailDto dto, UriComponentsBuilder uriBuilder ){
        Student s = dto.toStudent();
        try{
        Student saved = regsService.createStudent(s, dto.getHouseId(), dto.getCourseId());
        URI location = uriBuilder.path("/student/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}