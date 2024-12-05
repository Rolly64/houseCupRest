package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/scores")
public class ScoreController {
    ScoreService scoreService;
    RegisterService regService;

    @Autowired
    public ScoreController(ScoreService scoreService, RegisterService regService) {
        this.scoreService = scoreService;
        this.regService = regService;
    }

//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){
//        Score score = dto.toScore();
//        var os = regService.findStudentById(dto.getStudentId());
//        var ot = regService.findTeacherById(1L);
//
//        if (os.isEmpty()) {
//            return new ResponseEntity<>("Studente non trovato", HttpStatus.NOT_FOUND);
//        }
//
//        if (ot.isEmpty()) {
//            return new ResponseEntity<>("Insegnante non trovato", HttpStatus.NOT_FOUND);
//        }
//
//        score.setStudent(os.get());
//        score.setTeacher(ot.get());
//
//        Score created = scoreService.createScore(score);
//        URI location = uriBuilder.path("/scores/{id}").buildAndExpand(created.getId()).toUri();
//        return ResponseEntity.created(location).body(new ScoreDto(created));
//    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder) throws EntityNotFoundException {
        Score score = dto.toScore();
        try {
            Score fullScore = scoreService.setScoreWithStudentAndTeacher(score, dto.getStudentId(), dto.getTeacherId());
            Score created = scoreService.createScore(fullScore);
            URI location = uriBuilder.path("/scores/{id}").buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(location).body(new ScoreDto(created));
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@RequestBody ScoreDto dto, @PathVariable long id) {
        if (id != dto.getId()) {
            return ResponseEntity.badRequest().body("Gli id della richiesta e del body non coincidono");
        }

        try {
            Score score = dto.toScore();
            Score fullScore = scoreService.setScoreWithStudentAndTeacher(score, dto.getStudentId(), dto.getTeacherId());
            var os = scoreService.updateScore(fullScore);

            if (os.isPresent()) {
                return ResponseEntity.ok(new ScoreDto(os.get()));
            }
            return ResponseEntity.notFound().build();

        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        Optional<Score> os = scoreService.deleteById(id);
        return os.map(score -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

}
