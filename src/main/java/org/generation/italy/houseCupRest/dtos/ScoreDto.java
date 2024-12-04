package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record ScoreDto (long id, int points, String motivation, String assignDate,
                        long studentId,String studentFullName, long teacherId, String teacherFullName) {
    public static ScoreDto fromScore(Score s){
        return new ScoreDto(s.getId(), s.getPoints(), s.getMotivation(),
                s.getAssignDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                s.getStudent().getId(), String.format("%s %s", s.getStudent().getFirstname(), s.getStudent().getSurname()),
                s.getTeacher().getId(), String.format("%s %s", s.getTeacher().getFirstname(), s.getTeacher().getSurname()));
    }
    public Score toScore(){
        var s = new Score();
        s.setId(this.id);
        s.setPoints(this.points);
        s.setMotivation(this.motivation);
        s.setAssignDate(LocalDate.parse(this.assignDate));
        return s;
    }

    public long getId() {
        return id;
    }
}
