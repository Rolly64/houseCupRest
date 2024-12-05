package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.HouseDto;
import org.generation.italy.houseCupRest.dtos.ScoreDto;
import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.services.HouseService;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    private HouseService houseService;
    private RegisterService registerService;

    public HouseController(HouseService houseService, RegisterService registerService) {
        this.houseService = houseService;
        this.registerService = registerService;
    }

    @GetMapping
    public ResponseEntity<List<HouseDto>> getAllHouses(){ // VERY BAD
        return ResponseEntity.ok(registerService.getAllHouses().stream().map(HouseDto::fromHouse).toList());
    }
    @GetMapping("/{id}/bStud")
    public ResponseEntity<List<StudentDto>> bestStudentsByHouseId(@PathVariable long id){
        List<StudentDto> bestStud = houseService.bestStudentsByHouseId(id).stream().map(StudentDto::new).toList();
        if(!bestStud.isEmpty()){
            return ResponseEntity.ok(bestStud);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{houseId}/{courseId}/bStud")
    public ResponseEntity<List<StudentDto>> bestStudentsByHouseAndClassId(@PathVariable long houseId, @PathVariable long courseId){
        List<StudentDto> bestStud = houseService.bestStudentsByHouseAndClassId(houseId, courseId).stream().map(StudentDto::new).toList();
        if(!bestStud.isEmpty()){
            return ResponseEntity.ok(bestStud);
        }
        return ResponseEntity.notFound().build();
    }
}
