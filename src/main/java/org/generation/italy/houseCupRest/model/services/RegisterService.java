package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.exceptions.ResourceNotFoundException;

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
    Student createStudent(Student toSave, long houseId, long courseId) throws ResourceNotFoundException;
    void deleteStudent(long id) throws ResourceNotFoundException;
    void updateStudent(Student s, long houseId, long courseId) throws ResourceNotFoundException;
    Optional<Student> findStudentById(long id);
    Optional<Teacher> findTeacherById(long id);
    Optional<Course> deleteCourseById(long id);
    Optional<Course> updateCourse(Course course);
    Course create(Course course);
    List<Course> findActiveCourseByNamesContains(String className);
    List<Course> findByClassNameContains(String className);
    List<Course> findActiveCourses();
    List<Student> findStudentsWithBestSingleScoreByHouseId(long id) throws IdDoesNotExistException;
    List<Student> findStudentsWithBestSingleScoreByHouseIdAndClassID(long houseId, long courseId) throws IdDoesNotExistException;
    List<Student> findBestStudentsByHouseId(long id) throws IdDoesNotExistException;
    List<Student> findBestStudentsByHouseIdAndClassID(long houseId, long courseId) throws IdDoesNotExistException;
    List<Student> findStudentWithScoreReasonContains(String reason) throws ResourceNotFoundException;


}