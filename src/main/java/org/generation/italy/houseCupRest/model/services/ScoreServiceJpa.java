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
import org.springframework.stereotype.Service;

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
        Optional<Score> scoreToDelete = scoreRepositoryJpa.findById(id); // Cerca l'entità nel database
        scoreToDelete.ifPresent(score -> scoreRepositoryJpa.deleteById(id)); // Se l'entità esiste, la elimina
        return scoreToDelete; // Restituisce l'entità eliminata o Optional vuoto se non esisteva
    }
    @Override
    public Optional<Score> updateScore(Score score) {

        //Cerca un oggetto Score nel repository tramite il suo ID, restituendo un Optional per gestire il caso in cui l'ID non esista
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
}