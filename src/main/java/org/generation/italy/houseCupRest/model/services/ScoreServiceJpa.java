package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.repositories.ScoreRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.TeacherRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreServiceJpa implements ScoreService{
    private ScoreRepositoryJpa scoreRepositoryJpa;
    private StudentRepositoryJpa studentRepo;
    private TeacherRepositoryJpa teacherRepo;

    @Autowired
    public ScoreServiceJpa(ScoreRepositoryJpa scoreRepositoryJpa, StudentRepositoryJpa studentRepo, TeacherRepositoryJpa teacherRepo) {
        this.scoreRepositoryJpa = scoreRepositoryJpa;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public Score addScore(Score score) {
        return scoreRepositoryJpa.save(score);
    }

    @Override
    public Score save(Score score) {
        return scoreRepositoryJpa.save(score);
    }

    @Override
    public Optional<Score> findById(long id) {
        return scoreRepositoryJpa.findById(id);
    }

    @Override
    public Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException {
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Teacher> teacher = teacherRepo.findById(teacherId);
        if(student.isEmpty() || teacher.isEmpty()){
            throw new EntityNotFoundException("Entity not found", student.isEmpty() ?
            Student.class.getSimpleName(): Teacher.class.getSimpleName());
        }
        score.setStudent(student.get());
        score.setTeacher(teacher.get());
        scoreRepositoryJpa.save(score);
        return score;
    }

    @Override
    public Score updateScore(Score s) throws IdDoesNotExistException {
        Optional<Score> optScore = scoreRepositoryJpa.findById(s.getId());
        if(optScore.isEmpty()) {
            throw new IdDoesNotExistException("Id doesnt exist, cant update");
        }
        Score score = optScore.get();
        scoreRepositoryJpa.save(score);
        return score;
        }

    @Override
    public Score deleteScore(Score s) throws IdDoesNotExistException {
        Optional<Score> optScore = scoreRepositoryJpa.findById(s.getId());
        if (optScore.isEmpty()) {
            throw new IdDoesNotExistException("Id doesn't exist, can't delete");
        }
        Score score = optScore.get();
        scoreRepositoryJpa.delete(score);
        return score;
    }

}
