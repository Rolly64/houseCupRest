package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.HouseDto;
import org.generation.italy.houseCupRest.dtos.HouseRankingDto;
import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.repositories.HouseRepositoryJpa;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.generation.italy.houseCupRest.model.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    HouseService houseService;
    ScoreService scoreService;
    RegisterService registerService;

    @Autowired
    public RankingController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    public ResponseEntity<HouseRankingDto> getRankings(){
        List<HouseRanking> rankings = this.houseService.getRankings();
        HouseRankingDto dto = new HouseRankingDto(rankings);
        return ResponseEntity.ok(dto);
    }




}
