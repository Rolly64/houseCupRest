package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScoreDto {
    private long id;
    private int points;
    private String reason, studentFullName, teacherFullName;
    private long studentId, teacherId;
    private String assignDate;

    public ScoreDto() {

    }
    public ScoreDto(long id, int points, String reason, long studentId, String studentFullName, long teacherId, String teacherFullName, String assignDate) {
        this.id = id;
        this.points = points;
        this.reason = reason;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.assignDate = assignDate;
        this.studentFullName = studentFullName;
        this.teacherFullName = teacherFullName;
    }
    public ScoreDto(Score score){
        this.id = score.getId();
        this.points = score.getPoints();
        this.reason = score.getMotivation();
        this.assignDate = score.getAssignDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.studentId = score.getStudent().getId();
        this.studentFullName = String.format("%s %s", score.getStudent().getFirstname(), score.getStudent().getSurname());
        this.teacherId = score.getTeacher().getId();
        this.teacherFullName = String.format("%s %s", score.getTeacher().getFirstname(), score.getTeacher().getSurname());
    }
    public Score toScore() {
        Score score = new Score();
        score.setId(id);
        score.setPoints(points);
        score.setMotivation(reason);
        score.setAssignDate(LocalDate.parse(assignDate));
        return score;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
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

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
