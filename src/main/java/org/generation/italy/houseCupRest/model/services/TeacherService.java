package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Teacher;

import java.util.Optional;

public interface TeacherService {
    Optional<Teacher> findById(long id);
}
