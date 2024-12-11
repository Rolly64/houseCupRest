package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.HouseDto;
import org.generation.italy.houseCupRest.dtos.HouseRankingDto;
import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.repositories.HouseRepositoryJpa;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    HouseService houseService;
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
