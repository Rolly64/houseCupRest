package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/score")
public class ScoreController {
    private ScoreService scoreService;
    private RegisterService registerService;

    public ScoreController(ScoreService scoreService, RegisterService registerService) {
        this.scoreService = scoreService;
        this.registerService = registerService;
    }

//    @PostMapping
//    public ResponseEntity<ScoreDto> create(@RequestBody ScoreDto scoreDto, UriComponentsBuilder uriBuilder) {
//        Score score = scoreDto.toScore();
//        Optional<Student> oStudent = registerService.findStudentById(scoreDto.getStudentId());
//        Optional<Teacher> oTeacher = registerService.findTeacherById(scoreDto.getTeacherId());
//
//        if(oStudent.isEmpty() || oTeacher.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        score.setStudent(oStudent.get());
//        score.setTeacher(oTeacher.get());
//        Score scoreCreated = scoreService.save(score);
//        URI location = uriBuilder.path("/score/{id}").buildAndExpand(scoreCreated.getId()).toUri();
//
//        //.path mi specifica qual è il percorso nel quale si troverà la risposta
//        //.buildAndExpand sostituisce il {id} con quello che c'è dentro
//
//        return ResponseEntity.created(location).body(new ScoreDto(scoreCreated));
//
//        //creo la responseEntity created invece di ok per far si che si crei uno status code 201 invece di 200, che conferma
//        //la creazione, inoltre ci sarà un header della risposta che avrà valore /score/ScoreCreated.getId() (che ha un valore)
//    }

    //Crea un nuovo punteggio
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScoreDto scoreDto, UriComponentsBuilder uriBuilder) {
        Score score = scoreDto.toScore();
        try {
            scoreService.saveScore(score, scoreDto.getStudentId(), scoreDto.getTeacherId());
            URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri();
            return ResponseEntity.created(location).body(new ScoreDto(score));
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ScoreDto scoreDto, @PathVariable long id) {
        if(scoreDto.getId() != id) {
            return ResponseEntity.badRequest().body("Gli id non coincidono");
        }
        Optional<Student> optStud= registerService.findStudentById(scoreDto.getStudentId());
        Optional<Teacher> optTeacher = registerService.findTeacherById(scoreDto.getTeacherId());
        if(optStud.isEmpty() || optTeacher.isEmpty()) {
            return ResponseEntity.badRequest().body("Studente o Teacher vuoto");
        }
        Score newScore = scoreDto.toScore();
        newScore.setStudent(optStud.get());
        newScore.setTeacher(optTeacher.get());
        scoreService.updateScore(newScore);
        return ResponseEntity.ok(new ScoreDto(newScore));
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestBody ScoreDto scoreDto, @PathVariable long id) {
        scoreService.deleteScoreById(id);
        return ResponseEntity.noContent().build();
    }
}
