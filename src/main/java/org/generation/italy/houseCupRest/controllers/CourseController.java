package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.CourseDetailDto;
import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.exception.EntityException;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/course")
public class CourseController {
    @Autowired
    private RegisterService regService;


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
        return oC.map(course -> ResponseEntity.ok(new CourseDetailDto(course)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
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
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id,@RequestBody CourseDto courseDto){
        Optional<Course> existingCourse = regService.findCourseById(id);
        if(existingCourse.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            Course courseToUpdate = existingCourse.get();
            courseToUpdate.setClassName(courseDto.getClassName());
            courseToUpdate.setStartDate(LocalDate.parse(courseDto.getStartDate()));
            courseToUpdate.setEndDate(LocalDate.parse(courseDto.getEndDate()));
            regService.updateCourse(courseToUpdate);
            return ResponseEntity.noContent().build();
        }
    }
}