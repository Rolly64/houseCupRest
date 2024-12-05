package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.services.HouseService;
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

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
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
