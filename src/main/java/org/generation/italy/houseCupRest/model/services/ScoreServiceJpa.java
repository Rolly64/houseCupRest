package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.*;
import org.generation.italy.houseCupRest.model.exceptions.EntityNotFoundException;
import org.generation.italy.houseCupRest.model.exceptions.IdNotFound;
import org.generation.italy.houseCupRest.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceJpa implements ScoreService{
    private ScoreRepositoryJpa scoreRepositoryJpa;
    private StudentRepositoryJpa studentRepositoryJpa;
    private TeacherRepositoryJpa teacherRepositoryJpa;
    private HouseRepositoryJpa houseRepositoryJpa;
    private CourseRepositoryJpa courseRepositoryJpa;
    @Autowired
    public ScoreServiceJpa(ScoreRepositoryJpa scoreRepositoryJpa, StudentRepositoryJpa studentRepositoryJpa, TeacherRepositoryJpa teacherRepositoryJpa,HouseRepositoryJpa houseRepositoryJpa,
                           CourseRepositoryJpa courseRepositoryJpa) {
        this.scoreRepositoryJpa = scoreRepositoryJpa;
        this.studentRepositoryJpa = studentRepositoryJpa;
        this.teacherRepositoryJpa = teacherRepositoryJpa;
        this.houseRepositoryJpa = houseRepositoryJpa;
        this.courseRepositoryJpa = courseRepositoryJpa;

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
             scores = scoreRepositoryJpa.findByStudentIdAndAssignDateAfterAndAssignDateBefore(id,startDate,endDate); //se ci sono entrambe le date
        }else if(startDate!=null){
             scores = scoreRepositoryJpa.findByStudentIdAndAssignDateAfter(id,startDate); // da quella data in poi
        }else if(endDate!=null){
             scores = scoreRepositoryJpa.findByStudentIdAndAssignDateAfterAndAssignDateBefore(id,startDate,endDate); // prima di quella data fine
        }else{
             scores = scoreRepositoryJpa.findByStudentId(id); //se non ci sono date
        }
        return scores;
    }

    @Override
    public List<Student> findTopStudentSingleScoreByHouse(long id) throws IdNotFound{
        Optional<House> optHouse = houseRepositoryJpa.findById(id);
        if(optHouse.isEmpty()){
            throw new IdNotFound("house not found");
        }
        return  scoreRepositoryJpa.findTopStudentSingleScoreByHouse(id);
    }

    @Override
    public List<Student> findBestStudentByHouseId(long id, LocalDate starDate, LocalDate endDate) throws IdNotFound {
        Optional<House> optHouse = houseRepositoryJpa.findById(id); //controllo se id della casa esiste nel database
        if(optHouse.isEmpty()){
            throw new IdNotFound("house not found");
        }
        return scoreRepositoryJpa.findBestStudentByHouseId(id,starDate, endDate);

    }

    @Override
    public List<Student> findBestStudentByHouseIdAndClassId(long houseId, long classId) throws IdNotFound {
        Optional<House> optHouse= houseRepositoryJpa.findById(houseId);
        if(optHouse.isEmpty()){
            throw new IdNotFound("house not found");
        }
        Optional<Course> optCourse= courseRepositoryJpa.findById(classId);
        if(optCourse.isEmpty()){
            throw new IdNotFound("course not found");
        }
        return scoreRepositoryJpa.findBestStudentByHouseIdAndClassId(houseId,classId);
    }

    @Override
    public List<Student> findTopStudentSingleScoreByHouseAndByClassId(long houseId, long classId) throws IdNotFound {
        Optional<House> optHouse= houseRepositoryJpa.findById(houseId);
        if(optHouse.isEmpty()){
            throw new IdNotFound("house not found");
        }
        Optional<Course> optCourse= courseRepositoryJpa.findById(classId);
        if(optCourse.isEmpty()){
            throw new IdNotFound("course not found");
        }
        return scoreRepositoryJpa.findTopStudentSingleScoreByHouseAndByClassId(houseId,classId);
    }
}

