package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceJpa implements StudentService{
    private StudentRepositoryJpa studentRepositoryJpa;

    public StudentServiceJpa(StudentRepositoryJpa studentRepositoryJpa) {
        this.studentRepositoryJpa = studentRepositoryJpa;
    }

    @Override
    public List<Score> scoreHistoryByStudentId(long id) {
        return studentRepositoryJpa.scoreHistoryByStudentId(id).stream().toList();
    }

    @Override
    public List<Score> scoreHistoryByStudentIdCurrentWeek(long id) {
        return studentRepositoryJpa.scoreHistoryByStudentId(id).stream()
                .filter(score -> score.getAssignDate().isAfter(LocalDate.now().minusDays(7))).toList();
    }
}
