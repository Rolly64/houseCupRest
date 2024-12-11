package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.HouseDto;
import org.generation.italy.houseCupRest.dtos.HouseRankingDto;
import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/house")
public class HouseController {
    HouseService houseService;
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }
    @GetMapping
    public ResponseEntity<?> getAllHouses(){
        List<House> houses = this.houseService.getAllHouses();
        //List<HouseRanking> hs = this.houseService.getRankings();
        List<HouseDto> houseDtos = houses.stream().map(HouseDto::new).toList();
        //HouseRankingDto dto = new HouseRankingDto(hs);
        return ResponseEntity.ok(houseDtos);
    }
}
