package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.repositories.CourseRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.HouseRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.TeacherRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterServiceJpa implements RegisterService{
    private CourseRepositoryJpa courseRepo;
    private HouseRepositoryJpa houseRepo;
    private StudentRepositoryJpa studentRepo;
    private TeacherRepositoryJpa teacherRepo;

    @Autowired
    public RegisterServiceJpa(CourseRepositoryJpa courseRepo, HouseRepositoryJpa houseRepo, StudentRepositoryJpa studentRepo, TeacherRepositoryJpa teacherRepo){
        this.courseRepo = courseRepo;
        this.houseRepo = houseRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepo.findAll();
    }

    @Override
    public Optional<Course> findCourseById(long id) {
        return courseRepo.findById(id);
    }

    @Override
    public Optional<House> findHouseById(long id) {
        return houseRepo.findById(id);
    }

    @Override
    public Optional<Teacher> findTeacherById(long id) {
        return teacherRepo.findById(id);
    }

    @Override
    public List<Teacher> findAllTeachers() { // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaah
        return teacherRepo.findAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student saveStudent(Student s) {
        return studentRepo.save(s);
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        return studentRepo.findById(id);
    }
    @Override
    public Optional <Course> deleteCourseById(long id) {
//        if(courseRepo.findById(id).isPresent()){
//            Optional <Course> courseFoundById = courseRepo.findById(id);
//            courseRepo.deleteById(id);
//            return courseFoundById;
//        }
//        return Optional.empty();
            Optional<Course> oC = courseRepo.findById(id);
            oC.ifPresent(course -> courseRepo.delete(course));
            return oC;
    }


    @Override
    public Optional<Course> updateCourse(Course course) {
        Optional<Course> oC = courseRepo.findById(course.getId());
        Course oldCourse = null;
        if(oC.isPresent()){
            oldCourse = new Course(oC.get().getId(),oC.get().getClassName(),oC.get().getStartDate(),oC.get().getEndDate());
            courseRepo.save(course);
        }
        return Optional.ofNullable(oldCourse);
    }

    @Override
    public Course create(Course course) {
        Course courseSaved = courseRepo.save(course);
        return courseSaved;
    }

    @Override
    public List<Course> findActiveCourseByNamesContains(String className) {
        return courseRepo.findByStartDateBeforeAndEndDateAfterAndClassNameContains(LocalDate.now(),LocalDate.now(), className);
    }

    @Override
    public List<Course> findByClassNameContains(String className) {
        return courseRepo.findByClassNameContains(className);
    }

    @Override
    public List<Course> findActiveCourses() {
        return courseRepo.findByStartDateBeforeAndEndDateAfter(LocalDate.now(),LocalDate.now());
    }
}