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

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceJpa implements ScoreService{
    private ScoreRepositoryJpa scoreRepoJpa;
    private StudentRepositoryJpa studentRepoJpa;
    private TeacherRepositoryJpa teacherRepoJpa;
    @Autowired
    public ScoreServiceJpa(ScoreRepositoryJpa scoreRepositoryJpa, StudentRepositoryJpa studentRepoJpa, TeacherRepositoryJpa teacherRepoJpa) {
        this.scoreRepoJpa = scoreRepositoryJpa;
        this.studentRepoJpa=studentRepoJpa;
        this.teacherRepoJpa=teacherRepoJpa;
    }

    @Override
    public Score save(Score score) {
        return scoreRepoJpa.save(score);
    }

    @Override
    public Optional<Score> findById(long id) {
        return scoreRepoJpa.findById(id);
    }

    @Override
    public Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException {
        Optional<Student> optStudent = studentRepoJpa.findById(studentId);
        Optional<Teacher> optTeacher = teacherRepoJpa.findById(teacherId);

        if(optStudent.isEmpty() | optTeacher.isEmpty()){
            throw  new EntityNotFoundException("Entity not found", optStudent.isEmpty() ? Student.class.getSimpleName() : Teacher.class.getSimpleName());
        }
        score.setStudent(optStudent.get());
        score.setTeacher(optTeacher.get());
        scoreRepoJpa.save(score);
        return score;
    }

    @Override
    public Score uptadeScore(Score score) throws IdDoesNotExistException {
        Optional<Score> optScore= scoreRepoJpa.findById(score.getId());
        if(optScore.isEmpty()){
            throw new IdDoesNotExistException("id doesn't exist, can't update");
        }
        scoreRepoJpa.save(optScore.get());
        return optScore.get();
    }

    @Override
    public void deleteScore(long id) throws IdDoesNotExistException {
        Optional<Score> optScore = scoreRepoJpa.findById(id);
        if(optScore.isEmpty()){
            throw  new IdDoesNotExistException("id doesn't exist, can't delete");
        }
        scoreRepoJpa.delete(optScore.get());
    }

    @Override
    public List<Score> findStudentScores(long id) throws EntityNotFoundException {
        Optional<Student> optStudent = studentRepoJpa.findById(id);
        if(optStudent.isEmpty()){
            throw new EntityNotFoundException("student not found", optStudent.getClass().getSimpleName());
        }
        return scoreRepoJpa.findByStudentId(id);
    }
}