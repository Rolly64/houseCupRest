package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.CourseDetailDto;
import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.exception.EntityException;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {

    private RegisterService regService;

    @Autowired
    public CourseController(RegisterService regService) {
        this.regService = regService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> findCourses() {
        List<Course> courses = regService.getAllCourses();
//        List<CourseDto> courseDtos = courses.stream().map(c -> new CourseDto(c)).toList();
        List<CourseDto> courseDtos = courses.stream().map(CourseDto::new).toList();
        return ResponseEntity.ok(courseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailDto> findById(@PathVariable long id) {
        var oC = regService.findCourseById(id);
        if (oC.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CourseDetailDto(oC.get()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        try {
            boolean success = regService.deleteCourseById(id);
            if (success) {
                return ResponseEntity.noContent().build();//quando trova qualcosa riporta errore 204
            } else {
                return ResponseEntity.notFound().build(); //quando non trova nulla riporta errore 404
            }
        } catch (EntityException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}