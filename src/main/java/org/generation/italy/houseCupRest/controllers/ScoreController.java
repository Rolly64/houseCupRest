package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.entities.Score;
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
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScoreDto dto, UriComponentsBuilder uriBuilder){ // il ? indica che non sai di che tipo sarà la response entity(esempio dto o stringa)
        Score score = dto.toScore(); //Converte il DTO in un oggetto Score, per prepararlo alla logica del servizio
        try {
            scoreService.saveScore(score, dto.studentId(), dto.teacherId()); //Salva il punteggio e associa lo studente e l'insegnante
            URI location = uriBuilder.path("/score/{id}").buildAndExpand(score.getId()).toUri(); //Costruisce l'URI del nuovo oggetto creato
            return ResponseEntity.created(location).body(ScoreDto.fromScore(score)); //Restituisce una risposta HTTP 201 con il corpo del nuovo oggetto creato
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.NOT_FOUND); //Restituisce una risposta HTTP 404 se uno degli ID non esiste
        }
    }
    @PutMapping
    public ResponseEntity<?> updateScore(@RequestBody ScoreDto scoreDto){
        Score score= scoreDto.toScore(); //Aggiorna il punteggio con i nuovi valori
        try{
            scoreService.updateScore(score);
            return ResponseEntity.ok(scoreDto); //Restituisce una risposta HTTP 200 con il DTO aggiornato
        }catch (IdNotFound e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST); //Restituisce una risposta HTTP 400 se l'ID non è valido
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ScoreDto> deleteScore(@PathVariable long id, UriComponentsBuilder uriBuilder) {
        Optional<Score> isDeleted = scoreService.deleteById(id); //Prova a eliminare il punteggio con l'ID fornito
        URI location = uriBuilder.path("/score/{id}").buildAndExpand(id).toUri(); //Costruisce l'URI per confermare l'eliminazione
        return isDeleted.map(score ->  ResponseEntity.ok(ScoreDto.fromScore(score))).orElseGet(() -> ResponseEntity.notFound().build());
        // Questo codice restituisce una ResponseEntity in base alla presenza o meno di un oggetto "score" all'interno dell'Optional "isDeleted".
        // - Se "isDeleted" contiene un valore (un oggetto score), viene mappato in un'istanza di ScoreDto utilizzando il metodo statico "fromScore(score)",
        //   e il risultato è incapsulato in una ResponseEntity con stato HTTP 200 (OK).
        // - Se "isDeleted" è vuoto, viene restituita una ResponseEntity con stato HTTP 404 (Not Found).
        // Il metodo "map" viene utilizzato per trasformare il valore presente nell'Optional, mentre "orElseGet" gestisce il caso in cui l'Optional sia vuoto.
    }
}