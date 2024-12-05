package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdNotFound;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/score")
public class ScoreController {
    private RegisterService regService;
    private ScoreService scoreService;
    @Autowired
    public ScoreController(RegisterService regService, ScoreService scoreService) {
        this.regService = regService;
        this.scoreService = scoreService;
    }

//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){
//        Score score = dto.toScore();
//        Optional<Student> oSt = regService.findStudentById(dto.studentId());
//        if(oSt.isEmpty()){
//            return new ResponseEntity<>("studente non trovato", HttpStatus.NOT_FOUND);
//        }
//        Optional<Teacher> oT = regService.findTeacherById(dto.teacherId());
//        if(oT.isEmpty()){
//            return new ResponseEntity<>("insegnante non trovato", HttpStatus.NOT_FOUND);
//        }
//        score.setStudent(oSt.get());
//        score.setTeacher(oT.get());
//        scoreService.save(score);
//        URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
//        return ResponseEntity.created(location).body(ScoreDto.fromScore(score));
//    }
    @PostMapping //crea un punteggio
    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){
        Score score = dto.toScore();
        try {
            scoreService.saveScore(score, dto.studentId(), dto.teacherId());
            URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(ScoreDto.fromScore(score));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{id}") //update di un punteggio
    public ResponseEntity<?> updateScore(@RequestBody ScoreDto scoreDto,@RequestParam long id){
        if(id!=scoreDto.id()){
            return ResponseEntity.badRequest().body("id non trovato");
        }
        Score score= scoreDto.toScore();
        try{
            scoreService.updateScore(score);
            return ResponseEntity.ok(scoreDto);

        }catch (IdNotFound e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ScoreDto> deleteScore(@PathVariable long id) {
        Optional<Score> isDeleted = scoreService.deleteById(id);
        return isDeleted.map(score ->
                ResponseEntity.ok(ScoreDto.fromScore(score))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("{id}/score") // punteggi di uno studente per una certa settimana
    public ResponseEntity<?> getScores(@PathVariable long id, @RequestParam(required = false)LocalDate startDate,@RequestParam(required = false) LocalDate endDate) {
        try {
            List<Score> scores = scoreService.findStudentScores(id, startDate, endDate);
            List<ScoreDto> scoresDto = scores.stream().map(ScoreDto::fromScore).toList();
            return ResponseEntity.ok(scoresDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/house/{id}/bestSingleScore") //lo studente con il singolo voto più alto all'interno della casata in un
    public ResponseEntity<List<StudentDto>> getStudentWithBestSingleScoreByHouseId(@PathVariable long id){
        List<Student> topScorers = scoreService.findTopStudentSingleScoreByHouse(id);
        if(topScorers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<StudentDto> studentDtos = topScorers.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/course/{courseId}/house/{houseId}/bestSingleScore") //lo studente con il singolo punteggio più alto di una casa e di una classe
    public ResponseEntity<List<StudentDto>> getStudentWithBestSingleScoreByHouseIdAndClassId(@PathVariable Long houseId, @PathVariable Long courseId){
        List<Student> topScorers = scoreService.findTopStudentSingleScoreByHouseAndByClassId(houseId, courseId);
        if(topScorers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<StudentDto> studentDtos = topScorers.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(studentDtos);
    }
    @GetMapping("/house/{id}/bestStudentForDate") //lo studente con la somma massima dei punteggi per una casa e per una certa settimana
    public ResponseEntity<List<StudentDto>> getBestStudentByHouseId(@PathVariable long id, @RequestParam(required = false)LocalDate startDate, @RequestParam(required = false)LocalDate endDate){
        List<Student> topScorers = scoreService.findBestStudentByHouseId(id, startDate, endDate);
        if(topScorers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<StudentDto> studentDtos = topScorers.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(studentDtos);

    }
    @GetMapping("/class/{classId}/house/{houseId}/bestStudent") //lo studente con la somma massima dei punteggi per una casa e una classe
    public ResponseEntity<List<StudentDto>> getBestStudentByHouseIdAndClassId(@PathVariable long houseId,@PathVariable long classId){
        List<Student> topScorers = scoreService.findBestStudentByHouseIdAndClassId(houseId,classId);
        if(topScorers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<StudentDto> studentDtos = topScorers.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(studentDtos);

    }

    @GetMapping("motivation/{word}/score")
    public ResponseEntity<List<StudentDto>> getStudentByFindWordInMotivation(@PathVariable  String word){
        List<Student> students = scoreService.findDistinctStudentByMotivationContaining(word);
        if(students.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<StudentDto> studentsDto = students.stream().map(StudentDto::new).toList();
        return ResponseEntity.ok(studentsDto);

    }
}