package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/student")
public class StudentController {
    private RegisterService registerService;
    @Autowired
    public StudentController(RegisterService registerService){
        this.registerService = registerService;
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        var sts = this.registerService.findAllStudents();
        var dto = sts.stream()
                .map(StudentDto::new)
                .toList();
        return ResponseEntity.ok(dto);
    }

}
