package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    public List<Score> scoreHistoryByStudentId(long id);
    public List<Score> scoreHistoryByStudentIdCurrentWeek(long id);
}
