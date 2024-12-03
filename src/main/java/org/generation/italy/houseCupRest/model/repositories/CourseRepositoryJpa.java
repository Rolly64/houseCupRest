package org.generation.italy.houseCupRest.model.repositories;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepositoryJpa extends JpaRepository<Course, Long>{
    List<Course> findByStartDateBeforeAndEndDateAfterAndClassNameContains(LocalDate now1, LocalDate now2, String className);
    List<Course> findByClassNameContains(String className);
    List<Course> findByStartDateBeforeAndEndDateAfter(LocalDate now, LocalDate now1);
}