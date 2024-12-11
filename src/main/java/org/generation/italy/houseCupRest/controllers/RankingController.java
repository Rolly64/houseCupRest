package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.*;
import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.repositories.HouseRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.ScoreRepositoryJpa;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.generation.italy.houseCupRest.model.services.ScoreServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    HouseService houseService;
    ScoreService scoreService;
    RegisterService regService;
    @Autowired
    public RankingController(HouseService houseService, ScoreService scoreService, RegisterService regService) {
        this.houseService = houseService;
        this.scoreService = scoreService;
        this.regService = regService;
    }

    @GetMapping
    public ResponseEntity<HouseRankingDto> getRankings(){
        List<HouseRanking> rankings = this.houseService.getRankings();
        HouseRankingDto dto = new HouseRankingDto(rankings);
        return ResponseEntity.ok(dto);
    }
}
