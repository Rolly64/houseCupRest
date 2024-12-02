package org.generation.italy.houseCupRest.model.services;

import jakarta.persistence.EntityNotFoundException;
import org.generation.italy.houseCupRest.exception.EntityException;
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
    public List<Course> getAllCourses() {
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
    public boolean deleteCourseById(long id) throws EntityException{
        try {
            var oc = courseRepo.findById(id); // Cerca il corso nel repository
            if (oc.isEmpty()) { // Se il corso non esiste
                return false; // Restituisce false, indicando che il corso non è stato trovato
            }
            courseRepo.delete(oc.get()); // Elimina il corso
            return true; // Restituisce true, indicando che il corso è stato cancellato con successo
        } catch (EntityException e) {
            // Gestisce qualsiasi eccezione che possa essere lanciata
            throw new EntityException(e.getMessage());
        }

    }

    @Override
    public void updateCourse(Course course) {
        if (!courseRepo.existsById(course.getId())) {
            throw new EntityNotFoundException("Corso non trovato");
        }
        courseRepo.save(course);
    }
}
