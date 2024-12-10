package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.HouseDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@CrossOrigin(origins="http://localhost:4200", allowedHeaders = "*")
@Controller
@RequestMapping("/house")
public class HouseController {

    HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping
    ResponseEntity<List<HouseDto>> getHouses(){
        List<House> houses = houseService.getAll();
        List<HouseDto> dtos = houses.stream().map(HouseDto::new).toList();
        return ResponseEntity.ok(dtos);
    }
}
