package org.generation.italy.houseCupRest.dtos;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;

import java.time.LocalDate;

public class ScoreDto {
    private long id;
    private int score;
    private String reason;
    private long studentId, teacherId;
    private LocalDate date;

    public ScoreDto() {
    }
    public ScoreDto(long id,int score, String reason, long studentId, long teacherId, LocalDate date) {
        this.id=id;
        this.score = score;
        this.reason = reason;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.date = date;
    }
    public ScoreDto(Score s){
        this.id=s.getId();
        this.score = s.getPoints();
        this.reason = s.getMotivation();
        this.studentId = s.getStudent().getId();
        this.teacherId = s.getTeacher().getId();
        this.date = s.getAssign_date();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Score toScore() {
        Score s = new Score();
        s.setId(id);
        s.setPoints(score);
        s.setMotivation(reason);
        s.setAssign_date(date);
        Student student = new Student();
        student.setId(studentId);
        s.setStudent(student);
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        s.setTeacher(teacher);
        return s;
    }

}
