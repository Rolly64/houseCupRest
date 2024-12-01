package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.CourseDetailDto;
import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/course")
public class CourseController {

    private RegisterService regService;
    @Autowired
    public CourseController(RegisterService regService) {
        this.regService = regService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> findCourses(){
        List<Course> courses = regService.getAllCourses();
//        List<CourseDto> courseDtos = courses.stream().map(c -> new CourseDto(c)).toList();
        List<CourseDto> courseDtos = courses.stream().map(CourseDto::new).toList();
        return ResponseEntity.ok(courseDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailDto> findById(@PathVariable long id){
        var oC = regService.findCourseById(id);
        if(oC.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CourseDetailDto(oC.get()));
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<CourseDto> deleteById(@PathVariable long id) {
        Optional<Course> isDeleted = regService.deleteCourseById(id);
        if (isDeleted.isPresent()) {
            return ResponseEntity.ok(new CourseDto((isDeleted.get())));
        }
        return ResponseEntity.notFound().build();

    }
    @PutMapping("/{id}/update")
    public ResponseEntity<CourseDto> updateById(@RequestBody Course course) {
        Optional <Course> isUpdated = regService.updateById(course);
        if(isUpdated.isPresent()){
            return ResponseEntity.ok(new CourseDto(isUpdated.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/create")
    public ResponseEntity<CourseDto> create(@RequestBody Course course){
        Optional<Course> isCreated = regService.create(course);
//        if(isCreated.isPresent()){
            return ResponseEntity.ok(new CourseDto(isCreated.get()));
//        }
//        return ResponseEntity.notFound().build();
    }
}
