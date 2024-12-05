package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Score;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScoreDto {
    private long id;
    private int points;
    private String motivation;
    private String assign_date;
    private long studentId;
    private String studentName;
    private long teacherId;
    private String teacherName;

    public ScoreDto() {
    }

    public ScoreDto(String teacherName, long teacherId, String studentName, long studentId, String assign_date, String motivation, int points, long id) {
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.studentName = studentName;
        this.studentId = studentId;
        this.assign_date = assign_date;
        this.motivation = motivation;
        this.points = points;
        this.id = id;
    }

    public ScoreDto(Score s) {
        this.id = s.getId();
        this.points = s.getPoints();
        this.motivation = s.getMotivation();
        this.assign_date = s.getAssign_date().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.studentId = s.getStudent().getId();
        this.studentName = String.format("%s %s", s.getStudent().getFirstname(), s.getStudent().getSurname());
        this.teacherId = s.getTeacher().getId();
        this.teacherName = String.format("%s %s", s.getTeacher().getFirstname(), s.getTeacher().getSurname());
    }

    public Score toScore() {
        Score s = new Score();
        s.setId(this.id);
        s.setPoints(this.points);
        s.setMotivation(this.motivation);
        s.setAssign_date(this.assign_date != null ? LocalDate.parse(this.assign_date) : null);
        return s;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
