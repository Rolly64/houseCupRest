package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.dtos.StudentDto;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    public List<Score> scoreHistoryByStudentId(long id);
    public List<Score> scoreHistoryByStudentIdCurrentWeek(long id);
    public List<Student> findByScoresMotivationContainingIgnoreCase(String word);
    public List<Student> findStudentsByBestSingleScore();
    public List<Student> findStudentsByBestSingleScoreAndHouseAndClassId(long houseId, long courseId);
}
