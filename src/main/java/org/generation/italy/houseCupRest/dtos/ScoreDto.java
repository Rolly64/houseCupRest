package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Score;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ScoreDto {
    private long id;
    private int points;
    private String motivation, assignDate;
    private long studentId;
    private String studentFullName;
    private long teacherId;
    private String teacherFullName;

    public ScoreDto() {}

    public ScoreDto(long id, int points, String motivation, String assignDate, long studentId, String studentFullName, long teacherId, String teacherFullName) {
        this.id = id;
        this.points = points;
        this.motivation = motivation;
        this.assignDate = assignDate;
        this.studentId = studentId;
        this.studentFullName = studentFullName;
        this.teacherId = teacherId;
        this.teacherFullName = teacherFullName;
    }

    public ScoreDto (Score score) {
        this.id = score.getId();
        this.points = score.getPoints();
        this.motivation = score.getMotivation();
        this.assignDate = score.getAssignDate().format(DateTimeFormatter.ISO_DATE);
        this.studentId = score.getStudent().getId();
        this.studentFullName = String.format("%s %s", score.getStudent().getFirstname(), score.getStudent().getSurname());
        this.teacherId = score.getTeacher().getId();
        this.teacherFullName = String.format("%s %s", score.getTeacher().getFirstname(), score.getTeacher().getSurname());
    }

    public Score toScore() {
        var score = new Score();
        score.setId(id);
        score.setPoints(points);
        score.setMotivation(motivation);
        score.setAssignDate(LocalDate.parse(assignDate));
        //gli id dello studente e del teacher li manderà tramite un servizio nel controller
        return score;
    }


    public long getId() {
        return id;
    }
    public int getPoints() {
        return points;
    }
    public String getMotivation() {
        return motivation;
    }
    public String getAssignDate() {
        return assignDate;
    }
    public long getStudentId() {
        return studentId;
    }
    public long getTeacherId() {
        return teacherId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public static List<ScoreDto> fromScores(List<Score> scores) {
        return scores.stream().map(ScoreDto::new).toList();
    }
}
