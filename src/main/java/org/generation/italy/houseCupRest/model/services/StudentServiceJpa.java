package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.repositories.ScoreRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceJpa implements StudentService{
    private StudentRepositoryJpa studentRepositoryJpa;
    private ScoreRepositoryJpa scoreRepositoryJpa;

    public StudentServiceJpa(StudentRepositoryJpa studentRepositoryJpa, ScoreRepositoryJpa scoreRepositoryJpa) {
        this.studentRepositoryJpa = studentRepositoryJpa;
        this.scoreRepositoryJpa = scoreRepositoryJpa;
    }

    @Override
    public List<Score> scoreHistoryByStudentId(long id) {
        return scoreRepositoryJpa.findByStudentId(id).stream().toList();
    }
    @Override
    public List<Score> scoreHistoryByStudentIdCurrentWeek(long id) {
        return scoreRepositoryJpa.findByStudentId(id).stream()
                .filter(score -> score.getAssignDate().isAfter(LocalDate.now().minusDays(7))).toList();
    }
    @Override
    public List<Student> findByScoresMotivationContainingIgnoreCase(String word){
        return studentRepositoryJpa.findByScoresMotivationContainingIgnoreCase(word);
    }
    @Override
    public List<Student> findStudentsByBestSingleScore() {
        return studentRepositoryJpa.findStudentsByBestSingleScore();
    }

    @Override
    public List<Student> findStudentsByBestSingleScoreAndHouseAndClassId(long houseId, long courseId) {
        return studentRepositoryJpa.findStudentsByBestSingleScoreAndHouseAndClass(courseId, houseId);
    }
}