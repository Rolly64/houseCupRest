package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.HouseRankingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ranking")
public class RankingController {
    @Autowired
    private
    @GetMapping
    public ResponseEntity<HouseRankingDto> getRankings(){

    }
}
