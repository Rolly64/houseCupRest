package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegisterService{

    //courses
    List<Course> findAllCourses();
    Optional<Course> findCourseById(long id);
    Optional<Course> deleteCourseById(long id);
    Optional<Course> updateCourse(Course course);
    Course create(Course course);
    List<Course> findActiveCourseByNamesContains(String className);
    List<Course> findByClassNameContains(String className);
    List<Course> findActiveCourses();

    //houses
    List<House> getAllHouses();
    Optional<House> findHouseById(long id);

    //students
    List<Student> findAllStudents();
    Optional<Student> findStudentById(long id);
    Student saveStudent(Student s);
    List<Score> showScoresByStudentId(Long id);
    List<Score> showWeeklyScoresByStudentId(Long id, LocalDate ld1, LocalDate ld2);
    List<Student> showBestStudentsByHouse(Long id);
    List<Student> showBestStudentsByHouseInAClass(Long courseId, Long houseId);
    List<Student> findStudentsByScoreMotivationLike(String word);
    List<Student> findStudentByMaxPoints();
    List<Student> findStudentsByMaxPointsAndCourseAndHouse(long courseId, long houseId);
    //teachers
    List<Teacher> findAllTeachers(); // ricordiamo di implementare teacher
    Optional<Teacher> findTeacherById(long teacherId);





}