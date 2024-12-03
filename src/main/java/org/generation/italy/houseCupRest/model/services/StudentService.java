package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Student;

import java.util.Optional;

public interface StudentService {
    Student findById(long id);
}
