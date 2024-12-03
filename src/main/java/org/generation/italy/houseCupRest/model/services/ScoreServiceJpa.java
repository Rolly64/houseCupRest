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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.util.List;
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
    public Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException{
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Teacher> teacher = teacherRepo.findById(teacherId);
        if (student.isEmpty() || teacher.isEmpty()) {
            throw new EntityNotFoundException("Entity not found", student.isEmpty() ?
                    Student.class.getSimpleName() : Teacher.class.getSimpleName());
        }
        score.setStudent(student.get());
        score.setTeacher(teacher.get());
        scoreRepositoryJpa.save(score);
        return score;
    }
    @Override
    public Score updateScore(Score score) throws IdDoesNotExistException {
        Optional<Score> opScore = scoreRepositoryJpa.findById(score.getId());
        if(opScore.isEmpty()){
            throw new IdDoesNotExistException("id does not exist, cannot update");
        }
        Score scr = opScore.get();
        scoreRepositoryJpa.save(scr);
        return scr;
    }
    @Override
    public void deleteScore(long id) throws IdDoesNotExistException {
        Optional<Score> opScore = scoreRepositoryJpa.findById(id);
        if (opScore.isEmpty()) {
            throw new IdDoesNotExistException("id does not exist, cannot delete");
        }
        Score sc = opScore.get();
        scoreRepositoryJpa.delete(sc);
    }
    @Override
    public List<Score> findStudentScores(long id) throws EntityNotFoundException {
        Optional<Student> opStudent = studentRepo.findById(id);
        if (opStudent.isEmpty()) {
            throw new EntityNotFoundException("student not found", opStudent.getClass().getSimpleName());
        }
        return scoreRepositoryJpa.findByStudentId(id);
    }

    @Override
    public List<Score> findStudentWeeklyScores(long id) throws EntityNotFoundException {
        Optional<Student> opStudent = studentRepo.findById(id);
        if (opStudent.isEmpty()) {
            throw new EntityNotFoundException("student not found", opStudent.getClass().getSimpleName());
        }
        return scoreRepositoryJpa.findByStudentIdAndAssignDateBetween(id, LocalDate.now(), LocalDate.now().plus(Period.ofDays(7)));
    }


}