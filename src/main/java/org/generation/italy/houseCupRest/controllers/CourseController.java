package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.CourseDetailDto;
import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
