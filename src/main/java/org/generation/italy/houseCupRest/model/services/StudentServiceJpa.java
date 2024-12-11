package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceJpa implements StudentService{
    private StudentRepositoryJpa studentRepositoryJpa;

    public StudentServiceJpa(StudentRepositoryJpa studentRepositoryJpa) {
        this.studentRepositoryJpa = studentRepositoryJpa;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepositoryJpa.findAll();
    }
}
