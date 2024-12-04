package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface RegisterService{

    List<Course> findAllCourses();
    List<House> getAllHouses();
    Optional<Course> findCourseById(long id);
    Optional<House> findHouseById(long id);
    List<Student> findAllStudents();
    List<Teacher> findAllTeachers(); // ricordiamo di implementare teacher
    Student saveStudent(Student s);
    Optional<Student> findStudentById(long id);
    Optional<Course> deleteCourseById(long id);
    Optional<Course> updateCourse(Course course);
    Course create(Course course);
    List<Course> findActiveCourseByNamesContains(String className);
    List<Course> findByClassNameContains(String className);
    List<Course> findActiveCourses();
    Optional<Teacher> findTeacherById(long id);
}