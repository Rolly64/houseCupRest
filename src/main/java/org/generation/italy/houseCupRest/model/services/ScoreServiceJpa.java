package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.repositories.ScoreRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.TeacherRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreServiceJpa implements ScoreService{
    private ScoreRepositoryJpa scoreRepo;
    private StudentRepositoryJpa studentRepo;
    private TeacherRepositoryJpa teacherRepo;
    @Autowired
    public ScoreServiceJpa(ScoreRepositoryJpa scoreRepo, StudentRepositoryJpa studentRepo, TeacherRepositoryJpa teacherRepo) {
        this.scoreRepo = scoreRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public Score save(Score score) {
        return scoreRepo.save(score);
    }

    @Override
    public Optional<Score> findById(long id) {
        return scoreRepo.findById(id);
    }

    @Override
    public Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException{
        Optional<Student> oStudent = studentRepo.findById(studentId);
        Optional<Teacher> oTeacher = teacherRepo.findById(teacherId);
        if(oStudent.isEmpty() || oTeacher.isEmpty()) {
            throw new EntityNotFoundException("Entità non trovata", oStudent.isEmpty() ?
                    Student.class.getSimpleName() : Teacher.class.getSimpleName());
        }
        score.setStudent(oStudent.get());
        score.setTeacher(oTeacher.get());
        scoreRepo.save(score);
        return score;
    }

    @Override
    public Score updateScore(Score score){
        scoreRepo.save(score);
        return score;

    }

    @Override
    public boolean deleteScoreById(long id) {
        Optional<Score> optScore = scoreRepo.findById(id);
        if(optScore.isPresent()) {
            scoreRepo.deleteById(id);
            return true;
        }
        return false;
    }


}