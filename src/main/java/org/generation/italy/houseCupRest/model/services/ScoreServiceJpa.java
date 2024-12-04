package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.repositories.ScoreRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.TeacherRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

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
    public Score save(Score score) {
        return scoreRepositoryJpa.save(score);
    }
    @Override
    public Score saveScore(Score score, long studentId, long teacherId) throws EntityNotFoundException{
        Optional<Student> oSt = studentRepositoryJpa.findById(studentId);
        Optional<Teacher> oT = teacherRepositoryJpa.findById(teacherId);
        if(oSt.isEmpty() || oT.isEmpty()){
            throw new EntityNotFoundException("entità non trovata", oSt.isEmpty() ?
                    Student.class.getSimpleName() : Teacher.class.getSimpleName());
        }
        score.setStudent(oSt.get());
        score.setTeacher(oT.get());
        scoreRepositoryJpa.save(score);
        return score;
    }

    @Override
    public Optional<Score> findById(long id) {
        return scoreRepositoryJpa.findById(id);
    }
    @Override
    public Optional<Score> deleteById(long id){
        scoreRepositoryJpa.deleteById(id);
        return scoreRepositoryJpa.findById(id);
    }

    @Override
    public Optional<Score> updateScore(Score score) {
        Optional<Score> oS = scoreRepositoryJpa.findById(score.getId());
        if(oS.isPresent()){
            Score oldScore = oS.get(); // Prendi l'entità esistente
            // Aggiorna solo i campi necessari
            oldScore.setPoints(score.getPoints());
            oldScore.setMotivation(score.getMotivation());
            oldScore.setAssignDate(score.getAssignDate());
            // Aggiungi altre proprietà che devono essere aggiornate

            // Salva l'entità aggiornata
            scoreRepositoryJpa.save(oldScore);

            return Optional.of(oldScore);
        }
        return Optional.empty(); // Se non trovato, restituisci Optional.empty
    }

    @Override
    public List<Score> findStudentScores(long id, LocalDate startDate,LocalDate endDate) throws EntityNotFoundException {
        Optional<Student> optStudent = studentRepositoryJpa.findById(id);
        List<Score> scores = null;
        if(optStudent.isEmpty()){
            throw new EntityNotFoundException("student not found", optStudent.getClass().getSimpleName());
        }
        if(startDate != null && endDate != null){
             scores = scoreRepositoryJpa.findByStudentIdAndAssignDateAfterAndAssignDateBefore(id,startDate,endDate);
        }else if(startDate!=null){
             scores = scoreRepositoryJpa.findByStudentIdAndAssignDateAfter(id,startDate);
        }else if(endDate!=null){
             scores = scoreRepositoryJpa.findByStudentIdAndAssignDateAfterAndAssignDateBefore(id,startDate,endDate);
        }else{
             scores = scoreRepositoryJpa.findByStudentId(id);
        }
        return scores;
    }

    @Override
    public List<Student> findTopScorerByHouse(long id) {
        return scoreRepositoryJpa.findTopScoringStudentsByHouse(id);
    }


}