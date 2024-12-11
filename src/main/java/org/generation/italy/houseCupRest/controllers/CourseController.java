package org.generation.italy.houseCupRest.controllers;

import org.generation.italy.houseCupRest.dtos.CourseDetailDto;
import org.generation.italy.houseCupRest.dtos.CourseDto;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private RegisterService regService;
    @Autowired
    public CourseController(RegisterService regService) {
        this.regService = regService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> findCourses(@RequestParam(required = false) String className, @RequestParam(required = false) Boolean active){
        List<Course> courses = null;
        if(className!=null && !className.isEmpty() && active!=null && active){
            courses = regService.findActiveCourseByNamesContains(className);
        }else if (className!=null && !className.isEmpty() && active == null) {
            courses = regService.findByClassNameContains(className);
        } else if ((className==null || className.isEmpty()) && active != null) {
            courses = regService.findActiveCourses();
        }else {
            courses = regService.findAllCourses();
        }
        List<CourseDto> courseDtos = courses.stream().map(CourseDto::new).toList();
        return ResponseEntity.ok(courseDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDetailDto> findById(@PathVariable long id){
        var oC = regService.findCourseById(id);
        return oC.map(course -> ResponseEntity.ok(new CourseDetailDto(course)))
                    .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> deleteById(@PathVariable long id) {
        Optional<Course> isDeleted = regService.deleteCourseById(id);
        return isDeleted.map(course -> ResponseEntity.ok(new CourseDto((course))))
                        .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@RequestBody CourseDto dto,@PathVariable long id) {
        if(id!= dto.getId()){
            return ResponseEntity.badRequest().body("Gli id della richiesta e del body non coincidono");
        }
        Course course = dto.toCourse();
        Optional<Course> isUpdated = regService.updateCourse(course);
        if(isUpdated.isPresent()){
            return ResponseEntity.ok(new CourseDto(isUpdated.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<CourseDto> create(@RequestBody CourseDto dto, UriComponentsBuilder uriBuilder){
        Course course = dto.toCourse();
        Course created = regService.create(course);
        URI location = uriBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(location).body(new CourseDto(course));
    }
}
