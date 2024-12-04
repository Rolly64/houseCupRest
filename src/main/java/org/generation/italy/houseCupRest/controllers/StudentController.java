package org.generation.italy.houseCupRest.controllers;



import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    private RegisterService registerService;
    private ScoreService scoreService;

    public StudentController(RegisterService registerService, ScoreService scoreService) {
        this.registerService = registerService;
        this.scoreService = scoreService;
    }

    //history punteggi di uno studente tramite id
    @GetMapping("/{id}/score")
    public ResponseEntity<?> scoreHistory(@PathVariable long id) {
        Optional<Student> student = registerService.findStudentById(id);
        if(student.isEmpty()) {
            return ResponseEntity.badRequest().body("Non esiste uno studente con questo id");
        }
        List<Score> scores = registerService.showScoresByStudentId(id);
        return ResponseEntity.ok(scores);
    }

    
}
