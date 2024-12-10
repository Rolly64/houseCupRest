package org.generation.italy.houseCupRest.controllers;



import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.dtos.StudentDetailDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    private RegisterService registerService;
    private ScoreService scoreService;

    public StudentController(RegisterService registerService, ScoreService scoreService) {
        this.registerService = registerService;
        this.scoreService = scoreService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findStudents(@RequestParam (required = false) String motivation,
                                                         @RequestParam (required = false) Boolean byBestScore,
                                                         @RequestParam (required = false) Long courseId,
                                                         @RequestParam (required = false) Long houseId) {
        List<Student> students = null;
        if(motivation != null && !motivation.isBlank()) {
            students = registerService.findStudentsByScoreMotivationLike(motivation);
        } else if (byBestScore != null && byBestScore && courseId != null && houseId != null) {
            students = registerService.findStudentsByMaxPointsAndCourseAndHouse(courseId, houseId);
        } else if (byBestScore != null && byBestScore) {
            students = registerService.findStudentByMaxPoints();
        } else {
            students = registerService.findAllStudents();
        }
        return ResponseEntity.ok(StudentDto.fromStudents(students));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        Optional<Student> oSt = registerService.findStudentById(id);
        if(oSt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(StudentDetailDto.fromStudent(oSt.get()));
    }

    //history punteggi di uno studente tramite id
    @GetMapping("/{id}/score")
    public ResponseEntity<?> scoreHistory(@PathVariable long id) {
        Optional<Student> student = registerService.findStudentById(id);
        if(student.isEmpty()) {
            return ResponseEntity.badRequest().body("Non esiste uno studente con questo id");
        }
        List<Score> scores = registerService.showScoresByStudentId(id);
        return ResponseEntity.ok(ScoreDto.fromScores(scores));
    }

    //history punteggi di uno studente tramite id per la settimana corrente
    @GetMapping("/{id}/score/week")
    public ResponseEntity<?> weeklyScoreHistory(@PathVariable long id) {
        List<Score> scores = registerService.showWeeklyScoresByStudentId(id, LocalDate.now().with(DayOfWeek.MONDAY), LocalDate.now());
        return ResponseEntity.ok(ScoreDto.fromScores(scores));
    }

    //migliori studenti di una determinata casa
    @GetMapping("/house/{id}")
    public ResponseEntity<?> bestStudentsByHouse(@PathVariable long id) {
        List<Student> bestStudents = registerService.showBestStudentsByHouse(id);
        return ResponseEntity.ok(StudentDto.fromStudents(bestStudents));
    }

    //migliori studenti di una determinata casa e una determinata classe
    @GetMapping("/course/{courseId}/house/{houseId}/bestStudents")
    public ResponseEntity<?> bestStudentsByHouseInAClass(@PathVariable long courseId, @PathVariable long houseId) {
        List<Student> bestStudents = registerService.showBestStudentsByHouseInAClass(courseId, houseId);
        return ResponseEntity.ok(StudentDto.fromStudents(bestStudents));
    }

    @PostMapping //TODO
    public ResponseEntity<StudentDetailDto> createStudent(@RequestBody StudentDetailDto dto){
        Student s = dto.toStudent();
        return ResponseEntity.ok().build();
    }
}