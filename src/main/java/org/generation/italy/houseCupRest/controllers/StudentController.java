package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.generation.italy.houseCupRest.model.services.ScoreServiceJpa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    ScoreService scoreService;
    RegisterService registerService;
    HouseService houseService;

    public StudentController(ScoreService scoreService, RegisterService registerService, HouseService houseService) {
        this.scoreService = scoreService;
        this.registerService = registerService;
        this.houseService = houseService;
    }

    @GetMapping("/{id}/score")
    ResponseEntity<?> getStudentScoresById(@PathVariable long id){
        try{
            List<Score> scores = scoreService.findStudentScores(id);


        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
