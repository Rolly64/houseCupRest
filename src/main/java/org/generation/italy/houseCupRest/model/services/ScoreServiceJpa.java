package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.StudentMvp;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.repositories.ScoreRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.TeacherRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceJpa implements ScoreService{
    private ScoreRepositoryJpa scoreRepositoryJpa;
    private StudentRepositoryJpa studentRepositoryJpa;
    private TeacherRepositoryJpa teacherRepositoryJpa;
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
    public List<Score> getAllScore() {
        return scoreRepositoryJpa.findAll();
    }

    @Override
    public Optional<Score> updateScore(Score score) {
        Optional<Score> oS = scoreRepositoryJpa.findById(score.getId());
        Score oldScore = null;
        if (oS.isPresent()) {
            oldScore = new Score(oS.get().getId(),oS.get().getPoints(),oS.get().getMotivation(),oS.get().getAssign_date(),
                                 oS.get().getStudent(),oS.get().getTeacher());
            scoreRepositoryJpa.save(score);
        }
        return Optional.ofNullable(oldScore);
    }

    @Override
    public Optional<Score> deleteScoreById(long id) {
        Optional<Score> oS = scoreRepositoryJpa.findById(id);
        oS.ifPresent(score -> scoreRepositoryJpa.delete(score));
        return oS;
    }

    @Override
    public Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException {
        Optional<Student> oSt = studentRepositoryJpa.findById(studentId);
        Optional<Teacher> oT = teacherRepositoryJpa.findById(teacherId);
        if(oSt.isEmpty() || oT.isEmpty()){
            throw new EntityNotFoundException(String.format("entità %s non trovata",oSt.isEmpty()?"Student" : "Teacher"));
        }
        score.setStudent(oSt.get());
        score.setTeacher(oT.get());
        scoreRepositoryJpa.save(score);
        return score;
    }

    @Override
    public List<Score> findPointsByStudentId(long studentId) {
        return scoreRepositoryJpa.findScoresByStudentId(studentId);
    }

    @Override
    public List<Score> findStudentWeekScores(long studentId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
        return scoreRepositoryJpa.findCurrentWeekScore(startOfWeek, endOfWeek, studentId);
    }

    @Override
    public List<StudentMvp> findMvpByHouseId(long houseId) {
        return scoreRepositoryJpa.findMvpByHouseId(houseId);
    }

    @Override
    public List<Student> findStudentByKeyWord(String keyWord) {
        return scoreRepositoryJpa.findStudentByKeyWord(keyWord);
    }

    @Override
    public List<Student> findHighestSingleScorerBySingleScore() {
        return scoreRepositoryJpa.findHighestSingleScorerBySingleScore();
    }

    @Override
    public List<Student> findTheBestStudentsByClassAndHouseId(Long houseId, Long courseId) {
        return scoreRepositoryJpa.findTheBestStudentsByClassAndHouseId(houseId, courseId);
    }


}