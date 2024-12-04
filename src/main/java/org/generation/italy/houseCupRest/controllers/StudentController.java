package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.model.services.HouseService;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentController {
        ScoreService scoreService;
        RegisterService registerService;
        HouseService houseService;

    public StudentController(ScoreService scoreService, RegisterService registerService, HouseService houseService){
        this.scoreService = scoreService;
        this.registerService = registerService;
        this.houseService = houseService;
    }

}
