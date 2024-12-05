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
    private ScoreRepositoryJpa scoreRepositoryJpa;
    private TeacherRepositoryJpa teacherRepositoryJpa;
    private StudentRepositoryJpa studentRepositoryJpa;

    @Autowired
    public ScoreServiceJpa(ScoreRepositoryJpa scoreRepositoryJpa, StudentRepositoryJpa studentRepositoryJpa, TeacherRepositoryJpa teacherRepositoryJpa) {
        this.scoreRepositoryJpa = scoreRepositoryJpa;
        this.studentRepositoryJpa = studentRepositoryJpa;
        this.teacherRepositoryJpa = teacherRepositoryJpa;
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
    public Optional<Score> updateScore(Score score) {
        Optional<Score> os = scoreRepositoryJpa.findById(score.getId());
        Score oldScore = null;
        if (os.isPresent()) {
            oldScore = new Score(os.get().getId(), os.get().getPoints(), os.get().getMotivation(),
                                os.get().getAssign_date(), os.get().getStudent(), os.get().getTeacher());
            scoreRepositoryJpa.save(score);
        }
        return Optional.ofNullable(oldScore);
    }

    @Override
    public Score createScore(Score score) {
        return scoreRepositoryJpa.save(score);
    }

    @Override
    public Optional<Score> deleteById(long id) {
        Optional<Score> os = scoreRepositoryJpa.findById(id);
        os.ifPresent(score -> scoreRepositoryJpa.delete(score));
        return os;
    }

    @Override
    public Score setScoreWithStudentAndTeacher(Score score, long studentId, long teacherId) throws EntityNotFoundException {
        var os = studentRepositoryJpa.findById(studentId);
        var ot = teacherRepositoryJpa.findById(teacherId);

        if (os.isEmpty() || ot.isEmpty()) {
            throw new EntityNotFoundException("Entità non trovata", os.isEmpty() ?
                    Student.class.getSimpleName() : Teacher.class.getSimpleName());
        }
        score.setStudent(os.get());
        score.setTeacher(ot.get());

        return score;
    }
}