package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScoreDto {
    private long id;
    private int points;
    private String motivation;
    private String assignDate;
    private long studentId;
    private long teacherId;

    public ScoreDto() {
    }

    public ScoreDto(long id, int points, String motivation, String assignDate, long studentId, long teacherId) {
        this.id = id;
        this.points = points;
        this.motivation = motivation;
        this.assignDate = assignDate;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }
    public ScoreDto(Score s) {
        this.id = s.getId();
        this.points = s.getPoints();
        this.motivation = s.getMotivation();
        this.assignDate = s.getAssign_date().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.studentId = s.getStudent().getId();
        this.teacherId = s.getTeacher().getId();
    }
    public Score toScore() {
        Score score = new Score();
        score.setId(id);
        score.setPoints(points);
        score.setMotivation(motivation);
        score.setAssign_date(LocalDate.parse(assignDate));
        return score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}

