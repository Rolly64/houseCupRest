package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.StudentDetailsDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    private RegisterService registerService;

    public StudentController(StudentService studentService, RegisterService registerService) {
        this.studentService = studentService;
        this.registerService = registerService;
    }

    @Autowired


    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<Student> students = this.studentService.getAllStudents();
        List<StudentDto> studentsDto = students.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(studentsDto);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDetailsDto dto){
        Student s = dto.toStudent();
        try{
            Student saved = registerService.createStudent(s, dto.getHouse().getId(),dto.getCourse().getId());
            URI location = uriBuilder.path("/students/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(location).body(new StudentDetailsDto(saved));
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
